package textmining;

import ch.uzh.ifi.seal.changedistiller.ChangeDistiller;
import ch.uzh.ifi.seal.changedistiller.distilling.FileDistiller;
import ch.uzh.ifi.seal.changedistiller.model.entities.SourceCodeChange;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class ExtractChange {

    private static final String IMPORT = "+import";
    private static final String PACKAGE = "+package";
    private static final String SPACE = " ";
    private static final String EMPTY = "";
    private static final String DELETE = "Delete";
    private static final String BREAKE_N = "\n";
    private static final String ADDITION = "+";
    private static final String POINT = ".";

    private ExtractClass extractClass = new ExtractClass();

    public String extract(String oldRaw, String raw, String diff) throws IOException {
        File temp = File.createTempFile("pattern", ".suffix");
        File fTemp = File.createTempFile("pattern2", ".suffix");

        temp.deleteOnExit();
        fTemp.deleteOnExit();

        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
        BufferedWriter fOut = new BufferedWriter(new FileWriter(fTemp));
        out.write(StringUtils.isBlank(oldRaw) ? extractClass.extract(raw) : oldRaw);
        fOut.write(raw);
        out.close();
        fOut.close();

        FileDistiller distiller = ChangeDistiller.createFileDistiller(ChangeDistiller.Language.JAVA);
        distiller.extractClassifiedSourceCodeChanges(temp, fTemp);

        StringBuilder stringBuilder = new StringBuilder();
        distiller.getSourceCodeChanges().forEach(sourceCodeChange -> {
            if (!DELETE.equalsIgnoreCase(sourceCodeChange.getClass().getSimpleName()))
                stringBuilder.append(getParentName(sourceCodeChange)).append(SPACE);
        });

        Arrays.asList(Optional.ofNullable(diff).orElse(EMPTY).split(BREAKE_N)).forEach(s -> {
            if (s.startsWith(IMPORT))
                return;

            if (s.startsWith(PACKAGE))
                return;

            if (s.startsWith(ADDITION))
                stringBuilder.append(s).append(SPACE);
        });

        return stringBuilder.toString();
    }

    private String getParentName(SourceCodeChange codeChange) {

        String uniqueName = codeChange.getParentEntity().getUniqueName();
        int lastIndex = uniqueName.lastIndexOf(POINT);

        if (lastIndex < 0)
            return uniqueName;

        return uniqueName.substring(lastIndex + 1);
    }
}

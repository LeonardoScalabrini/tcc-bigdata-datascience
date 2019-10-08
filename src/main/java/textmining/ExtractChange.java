package textmining;

import ch.uzh.ifi.seal.changedistiller.ChangeDistiller;
import ch.uzh.ifi.seal.changedistiller.distilling.FileDistiller;
import ch.uzh.ifi.seal.changedistiller.model.entities.SourceCodeChange;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExtractChange {

    private static final String SPACE = " ";

    private static final String DELETE = "Delete";

    private ExtractClass extractClass = new ExtractClass();

    public List<String> extract(String oldRaw, String raw){
        try {
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

            List<String> changes = new ArrayList<String>();
            distiller.getSourceCodeChanges().forEach(sourceCodeChange -> {

                if (!DELETE.equalsIgnoreCase(sourceCodeChange.getClass().getSimpleName()))
                    changes.add(getChanges(raw, sourceCodeChange));
            });

            return changes;
        } catch (IOException e) {
        }

        return null;
    }

    private String getChanges(String raw, SourceCodeChange codeChange) {
        return codeChange.getParentEntity().getUniqueName()
                + SPACE
                + codeChange.getChangedEntity().getUniqueName();
    }
}

package textmining;

import ch.uzh.ifi.seal.changedistiller.ChangeDistiller;
import ch.uzh.ifi.seal.changedistiller.ast.java.JavaCompilation;
import ch.uzh.ifi.seal.changedistiller.ast.java.JavaCompilationUtils;
import ch.uzh.ifi.seal.changedistiller.distilling.FileDistiller;
import ch.uzh.ifi.seal.changedistiller.model.entities.SourceCodeChange;
import ch.uzh.ifi.seal.changedistiller.structuredifferencing.StructureDiffNode;
import ch.uzh.ifi.seal.changedistiller.structuredifferencing.StructureDifferencer;
import ch.uzh.ifi.seal.changedistiller.structuredifferencing.java.JavaStructureNode;
import ch.uzh.ifi.seal.changedistiller.structuredifferencing.java.JavaStructureTreeBuilder;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.jdt.internal.compiler.lookup.CompilationUnitScope;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExtractChange {

    public List<SourceCodeChange> extract(String oldRaw, String raw){
        try {
            File temp = File.createTempFile("pattern", ".suffix");
            File fTemp = File.createTempFile("pattern2", ".suffix");

            temp.deleteOnExit();
            fTemp.deleteOnExit();

            BufferedWriter out = new BufferedWriter(new FileWriter(temp));
            BufferedWriter fOut = new BufferedWriter(new FileWriter(fTemp));
            out.write(oldRaw);
            fOut.write(raw);
            out.close();
            fOut.close();

            FileDistiller distiller = ChangeDistiller.createFileDistiller(ChangeDistiller.Language.JAVA);
            distiller.extractClassifiedSourceCodeChanges(temp, fTemp);
            return distiller.getSourceCodeChanges();

        } catch (IOException e) {
        }

        return null;
    }

    private StructureDiffNode createDifferences(String lSource, String rSource) {
        StructureDifferencer differencer = new StructureDifferencer();
        differencer.extractDifferences(createStructureTree(lSource), createStructureTree(rSource));
        return differencer.getDifferences();
    }

    private JavaStructureNode createStructureTree(String source) {
        JavaCompilation compilation = JavaCompilationUtils.compile(source, "");
        CompilationUnitDeclaration cu = compilation.getCompilationUnit();
        JavaStructureNode root = new JavaStructureNode(JavaStructureNode.Type.CU, null, null, cu);
        cu.traverse(new JavaStructureTreeBuilder(root), (CompilationUnitScope) null);
        return root;
    }
}

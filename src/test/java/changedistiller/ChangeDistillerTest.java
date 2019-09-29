package changedistiller;

import ch.uzh.ifi.seal.changedistiller.ChangeDistiller;
import ch.uzh.ifi.seal.changedistiller.ast.java.JavaCompilation;
import ch.uzh.ifi.seal.changedistiller.ast.java.JavaCompilationUtils;
import ch.uzh.ifi.seal.changedistiller.distilling.FileDistiller;
import ch.uzh.ifi.seal.changedistiller.structuredifferencing.StructureDiffNode;
import ch.uzh.ifi.seal.changedistiller.structuredifferencing.StructureDifferencer;
import ch.uzh.ifi.seal.changedistiller.structuredifferencing.java.JavaStructureNode;
import ch.uzh.ifi.seal.changedistiller.structuredifferencing.java.JavaStructureTreeBuilder;
import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
import org.eclipse.jdt.internal.compiler.lookup.CompilationUnitScope;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ChangeDistillerTest {

    @Test
    public void teste(){
        String lSource = "public class Foo { class Bar { void method() {} } }";
        String fSource = "public class Foo { class Bar { void method() { int a = 12; } } }";
        StructureDiffNode structureDiffNode = createDifferences(lSource, fSource);
        System.out.println(structureDiffNode.getRight().getChildren().get(0).getName());

        try {
            // Create temp file.
            File temp = File.createTempFile("pattern", ".suffix");
            File fTemp = File.createTempFile("pattern2", ".suffix");

            // Delete temp file when program exits.
            temp.deleteOnExit();
            fTemp.deleteOnExit();

            // Write to temp file
            BufferedWriter out = new BufferedWriter(new FileWriter(temp));
            BufferedWriter fOut = new BufferedWriter(new FileWriter(fTemp));
            out.write(lSource);
            out.close();
            fOut.write(fSource);
            fOut.close();

            FileDistiller distiller = ChangeDistiller.createFileDistiller(ChangeDistiller.Language.JAVA);
            distiller.extractClassifiedSourceCodeChanges(temp, fTemp);
            System.out.println(distiller.getSourceCodeChanges().get(0).getChangedEntity().getUniqueName());

        } catch (IOException e) {
        }
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

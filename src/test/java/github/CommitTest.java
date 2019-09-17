package github;

import org.junit.Test;
import util.FileUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CommitTest {

    private JsonConverter jsonConverter = new JsonConverter();

    @Test
    public void deveSerializarCommit(){
        String json = FileUtil.loadJson("commit.json");
        Commit commit = jsonConverter.fromJson(json, Commit.class);
        assertEquals("019abc9980b9855bf04086c44b68f3282119c6b3", commit.getSha());
        assertEquals("Add license headers", commit.getMessage());
        assertEquals(".sonarcloud.properties", commit.getFiles().get(0).getFilename());
        assertEquals("@@ -1,3 +1,26 @@\n+#\n+# The MIT License\n+# Copyright (c) 2014 Ilkka Seppälä\n+#\n+# Permission is hereby granted, free of charge, to any person obtaining a copy\n+# of this software and associated documentation files (the \"Software\"), to deal\n+# in the Software without restriction, including without limitation the rights\n+# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n+# copies of the Software, and to permit persons to whom the Software is\n+# furnished to do so, subject to the following conditions:\n+#\n+# The above copyright notice and this permission notice shall be included in\n+# all copies or substantial portions of the Software.\n+#\n+# THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n+# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n+# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n+# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n+# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n+# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN\n+# THE SOFTWARE.\n+#\n+\n # Path to sources\n #sonar.sources=.\n #sonar.exclusions=", commit.getFiles().get(0).getPatch());
        assertEquals("commander/pom.xml", commit.getFiles().get(1).getFilename());
        assertEquals("@@ -1,3 +1,27 @@\n+<!--\n+\n+    The MIT License\n+    Copyright (c) 2014 Ilkka Seppälä\n+\n+    Permission is hereby granted, free of charge, to any person obtaining a copy\n+    of this software and associated documentation files (the \"Software\"), to deal\n+    in the Software without restriction, including without limitation the rights\n+    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n+    copies of the Software, and to permit persons to whom the Software is\n+    furnished to do so, subject to the following conditions:\n+\n+    The above copyright notice and this permission notice shall be included in\n+    all copies or substantial portions of the Software.\n+\n+    THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n+    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n+    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n+    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n+    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n+    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN\n+    THE SOFTWARE.\n+\n+-->\n <project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n   <modelVersion>4.0.0</modelVersion>\n   <parent>", commit.getFiles().get(1).getPatch());
        assertEquals("b874adc29693de254ce47759f543ee4eec19c19b", commit.getParents().get(0).getSha());
    }

    @Test
    public void deveSerializarCommits(){
        String json = FileUtil.loadJson("commits.json");
        List<Commit> commits = jsonConverter.fromJsons(json, Commit[].class);
        assertEquals("019abc9980b9855bf04086c44b68f3282119c6b3", commits.get(0).getSha());
    }
}
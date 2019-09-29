package textmining;

import github.Commit;

public class FindIssue {

    private static final String REGEX = "\\d+";
    private static final String HASHTAG = "#";

    public String find(Commit commit){

        if (commit == null || commit.getMessage() == null)
            return null;

        if (!commit.getMessage().contains(HASHTAG))
            return null;

        int length = commit.getMessage().length();
        int start = commit.getMessage().indexOf(HASHTAG) + 1;

        if (start == length)
            return null;

        String subStringStart = commit.getMessage().substring(start, length);

        String issue = null;

        for (int i = 0; i < subStringStart.length(); i++) {

            String digit = subStringStart.substring(0, i + 1);

            if(digit.matches(REGEX)){
                issue = digit;
                continue;
            }

            break;
        }

        return issue;
    }
}

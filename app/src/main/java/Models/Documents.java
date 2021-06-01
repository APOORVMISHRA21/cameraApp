package Models;

public class Documents {
    private String docId;
    private String docImage;
    private String docName;

    public Documents() {}

    public Documents(String docImage, String docName) {
        this.docImage = docImage;
        this.docName = docName;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocImage() {
        return docImage;
    }

    public void setDocImage(String docImage) {
        this.docImage = docImage;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }
}

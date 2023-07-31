package com.example.MyBookShopApp.data.BookStructure;

public enum BookFileType {

    PDF(".pdf"), DOCX(".docx");

    private final String fileExtensionString;

    BookFileType(String fileExtensionString) {
        this.fileExtensionString = fileExtensionString;
    }

    public static String getExtensionStringByTypeId(Integer typeId) {
        switch (typeId) {

            case 1: return BookFileType.PDF.fileExtensionString;
            case 2: return BookFileType.DOCX.fileExtensionString;
            default: return "";

        }
    }
}

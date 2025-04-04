package com.example.zoteroepubcovers;

import com.google.gson.annotations.SerializedName;

public class ZoteroItem {
    
    @SerializedName("key")
    private String key;
    
    @SerializedName("data")
    private ZoteroItemData data;
    
    @SerializedName("links")
    private ZoteroLinks links;
    
    // Nested class to represent item data
    public static class ZoteroItemData {
        @SerializedName("title")
        private String title;
        
        @SerializedName("creators")
        private ZoteroCreator[] creators;
        
        @SerializedName("contentType")
        private String contentType;
        
        @SerializedName("filename")
        private String filename;
    }
    
    // Nested class to represent creator data
    public static class ZoteroCreator {
        @SerializedName("firstName")
        private String firstName;
        
        @SerializedName("lastName")
        private String lastName;
        
        @SerializedName("creatorType")
        private String creatorType;
    }
    
    // Nested class to represent links
    public static class ZoteroLinks {
        @SerializedName("enclosure")
        private ZoteroLink enclosure;
        
        public ZoteroLink getEnclosure() {
            return enclosure;
        }
    }
    
    // Nested class to represent a link
    public static class ZoteroLink {
        @SerializedName("href")
        private String href;
        
        @SerializedName("type")
        private String type;
        
        public String getHref() {
            return href;
        }
    }
    
    public String getKey() {
        return key;
    }
    
    public String getTitle() {
        return data != null ? data.title : "";
    }
    
    public String getMimeType() {
        return data != null ? data.contentType : "";
    }
    
    public String getFilename() {
        return data != null ? data.filename : "";
    }
    
    public String getAuthors() {
        if (data == null || data.creators == null || data.creators.length == 0) {
            return "Unknown";
        }
        
        StringBuilder authorsBuilder = new StringBuilder();
        for (int i = 0; i < data.creators.length; i++) {
            ZoteroCreator creator = data.creators[i];
            if (creator.creatorType.equals("author")) {
                if (authorsBuilder.length() > 0) {
                    authorsBuilder.append(", ");
                }
                authorsBuilder.append(creator.lastName)
                        .append(", ")
                        .append(creator.firstName);
            }
        }
        
        return authorsBuilder.length() > 0 ? authorsBuilder.toString() : "Unknown";
    }
    
    public ZoteroLinks getLinks() {
        return links;
    }
}
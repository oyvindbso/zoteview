package oyvindbs.zotshelf;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferences {
    
    private static final String PREF_NAME = "zotshelfPrefs";
    private static final String KEY_ZOTERO_USER_ID = "zotero_user_id";
    private static final String KEY_ZOTERO_API_KEY = "zotero_api_key";
    private static final String KEY_ZOTERO_USERNAME = "zotero_username";
    private static final String KEY_SELECTED_COLLECTION = "selected_collection";
    private static final String KEY_SELECTED_COLLECTION_NAME = "selected_collection_name";
    private static final String KEY_DISPLAY_MODE = "display_mode";
    
    // Display mode constants
    public static final int DISPLAY_TITLE_ONLY = 0;
    public static final int DISPLAY_AUTHOR_ONLY = 1;
    public static final int DISPLAY_AUTHOR_TITLE = 2;
    
    private final SharedPreferences preferences;
    
    public UserPreferences(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    
    public boolean hasZoteroCredentials() {
        String userId = getZoteroUserId();
        String apiKey = getZoteroApiKey();
        String username = getZoteroUsername();
        
        return userId != null && !userId.isEmpty() 
                && apiKey != null && !apiKey.isEmpty()
                && username != null && !username.isEmpty();
    }
    
    public String getZoteroUserId() {
        return preferences.getString(KEY_ZOTERO_USER_ID, "");
    }
    
    public void setZoteroUserId(String userId) {
        preferences.edit().putString(KEY_ZOTERO_USER_ID, userId).apply();
    }
    
    public String getZoteroApiKey() {
        return preferences.getString(KEY_ZOTERO_API_KEY, "");
    }
    
    public void setZoteroApiKey(String apiKey) {
        preferences.edit().putString(KEY_ZOTERO_API_KEY, apiKey).apply();
    }
    
    public String getZoteroUsername() {
        return preferences.getString(KEY_ZOTERO_USERNAME, "");
    }
    
    public void setZoteroUsername(String username) {
        // Convert username to lowercase before saving
        if (username != null) {
            username = username.toLowerCase();
        }
        preferences.edit().putString(KEY_ZOTERO_USERNAME, username).apply();
    }

    public String getSelectedCollectionKey() {
        return preferences.getString(KEY_SELECTED_COLLECTION, "");
    }

    public void setSelectedCollectionKey(String collectionKey) {
        preferences.edit().putString(KEY_SELECTED_COLLECTION, collectionKey).apply();
    }

    public String getSelectedCollectionName() {
        return preferences.getString(KEY_SELECTED_COLLECTION_NAME, "All Collections");
    }

    public void setSelectedCollectionName(String collectionName) {
        preferences.edit().putString(KEY_SELECTED_COLLECTION_NAME, collectionName).apply();
    }
    
    public int getDisplayMode() {
        return preferences.getInt(KEY_DISPLAY_MODE, DISPLAY_TITLE_ONLY); // Default to title only
    }
    
    public void setDisplayMode(int displayMode) {
        preferences.edit().putInt(KEY_DISPLAY_MODE, displayMode).apply();
    }

    public void clearAll() {
        preferences.edit().clear().apply();
    }
}
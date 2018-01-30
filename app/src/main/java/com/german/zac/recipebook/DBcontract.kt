package com.german.zac.recipebook

import android.provider.BaseColumns

object RecipeEntryContract {
    // Table contents are grouped together in an anonymous object.
    class RecipeEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "RecipeBook"
            const val COLUMN_TITLE = "title"
            const val COLUMN_GROUP = "group"
            const val COLUMN_BODY = "body"
        }
    }
}

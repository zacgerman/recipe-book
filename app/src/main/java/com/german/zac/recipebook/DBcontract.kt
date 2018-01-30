package com.german.zac.recipebook

import android.provider.BaseColumns

object RecipeEntryContract {
    // Table contents are grouped together in an anonymous object.
    class RecipeEntry : BaseColumns {
        companion object {
            const val TABLE_NAME = "RecipeBook"
            const val COLUMN_TITLE = "Title"
            const val COLUMN_GROUP = "Group"
            const val COLUMN_BODY = "Body"
        }
    }
}

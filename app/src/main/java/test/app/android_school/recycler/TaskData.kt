package test.app.android_school.recycler

import com.google.gson.annotations.SerializedName

data class TaskData(

        @SerializedName ("id")
        var id: String,

        @SerializedName ("text")
        var text: String,

        @SerializedName ("importance")
        var importance: String,

        @SerializedName ("done")
        var done: Boolean,

        @SerializedName ("deadline")
        var deadline: Int,

        @SerializedName ("created_at")
        var createdAt: Int,

        @SerializedName ("updated_at")
        var updatedAt: Int,
) {
}
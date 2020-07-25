/*
     *
     * BH Apps
     * version 0.0.2
     * Methods for Get/Set Shared Preferences
     * bhapps.utilitools.preferences.kotlin.shared.GetSetSharedPreferences
     *
*/

package bhapps.utilitools.preferences.kotlin.shared

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

object GetSetSharedPreferences {

    //region SharedPref String
    fun getSharedPreferencesStringValueByKey(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String): String? {
        val settings = context.getSharedPreferences(SharedPreferencesName, 0)
        return settings.getString(SharedPreferencesKey, "")
    }

    fun setSharedPreferencesStringByKeyAndValue(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String,
        SharedPreferencesValue: String
    ): Boolean {
        var result = false

        val oldValue = getSharedPreferencesStringValueByKey(context, SharedPreferencesName, SharedPreferencesKey)

        val settings = context.getSharedPreferences(SharedPreferencesName, 0)
        val editor = settings.edit()
        editor.putString(SharedPreferencesKey, SharedPreferencesValue)
        if (editor.commit()) {
            Log.e(
                "ApplicationHelper",
                "key(" + SharedPreferencesKey + ")/value(" + SharedPreferencesValue + ")new(" + getSharedPreferencesStringValueByKey(
                    context,
                    SharedPreferencesName,
                    SharedPreferencesKey
                ) + ")/old(" + oldValue + ")"
            )
            result = true
        }

        return result

    }
    //endregion SharedPref String

    //region SharedPref Boolean
    fun getSharedPreferencesBooleanValueByKey(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String): Boolean {

        var result = false

        val settings = context.getSharedPreferences(SharedPreferencesName, 0)
        result = settings.getBoolean(SharedPreferencesKey, false)

        return result
    }

    fun setSharedPreferencesBooleanByKeyAndValue(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String,
        SharedPreferencesValue: Boolean
    ): Boolean {
        var result = false

        val oldValue = getSharedPreferencesBooleanValueByKey(
            context,
            SharedPreferencesName,
            SharedPreferencesKey)

        val settings = context.getSharedPreferences(SharedPreferencesName, 0)
        val editor = settings.edit()
        editor.putBoolean(SharedPreferencesKey, SharedPreferencesValue!!)
        if (editor.commit()) {
            Log.e(
                "ApplicationHelper",
                "key(" + SharedPreferencesKey + ")/value(" + SharedPreferencesValue + ")new(" + getSharedPreferencesBooleanValueByKey(
                    context,
                    SharedPreferencesName,
                    SharedPreferencesKey
                ) + ")/old(" + oldValue + ")"
            )
            result = true
        }

        return result

    }
    //endregion SharedPref Boolean

    //region SharedPref Int
    fun getSharedPreferencesIntValueByKey(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String): Int {

        var result = 0

        try {

            val settings = context.getSharedPreferences(SharedPreferencesName, 0)
            result = settings.getInt(SharedPreferencesKey, 0)

        } catch (error: Exception) {

            Log.e("ApplicationHelper", error.message.toString())

        }

        return result
    }

    fun setSharedPreferencesIntByKeyAndValue(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String,
        SharedPreferencesValue: Int
    ): Boolean {
        var result = false

        val oldValue = getSharedPreferencesIntValueByKey(context, SharedPreferencesName, SharedPreferencesKey)

        val settings = context.getSharedPreferences(SharedPreferencesName, 0)
        val editor = settings.edit()
        editor.putInt(SharedPreferencesKey, SharedPreferencesValue)
        if (editor.commit()) {
            Log.e(
                "ApplicationHelper",
                "key(" + SharedPreferencesKey + ")/value(" + SharedPreferencesValue + ")new(" + getSharedPreferencesIntValueByKey(
                    context,
                    SharedPreferencesName,
                    SharedPreferencesKey
                ) + ")/old(" + oldValue + ")"
            )
            result = true
        }

        return result

    }
    //endregion SharedPref Int

    //region SharedPref ArrayList
    fun checkSharedPreferencesArrayListExists(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String): Boolean {

        var result = false

        var list: ArrayList<String>? = ArrayList()
        val gson = Gson()
        val settings = context.getSharedPreferences(SharedPreferencesName, 0)
        val json = settings.getString(SharedPreferencesKey, null)
        val type = object : TypeToken<ArrayList<String>>() {

        }.type
        list = gson.fromJson<ArrayList<String>>(json, type)

        if (list != null) {
            if (list.size > 0) {
                result = true
            }
        }

        return result
    }

    fun getSharedPreferencesArrayListCount(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String): Int? {

        var result: Int? = 0

        var list: ArrayList<String>? = ArrayList()
        val gson = Gson()
        val settings = context.getSharedPreferences(SharedPreferencesName, 0)
        val json = settings.getString(SharedPreferencesKey, null)
        val type = object : TypeToken<ArrayList<String>>() {

        }.type
        list = gson.fromJson<ArrayList<String>>(json, type)

        if (list != null) {
            if (list.size > 0) {
                result = list.size
            }
        }

        return result
    }

    fun getSharedPreferencesArrayList(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String): ArrayList<String> {

        var list: ArrayList<String>? = ArrayList()
        val gson = Gson()
        val settings = context.getSharedPreferences(SharedPreferencesName, 0)

        val json = settings.getString(SharedPreferencesKey, null)
        val type = object : TypeToken<ArrayList<String>>() {

        }.type

        list = gson.fromJson<ArrayList<String>>(json, type)

        if (list == null) {
            list = ArrayList()
        }

        return list
    }

    fun buildSharedPreferencesArrayListString(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String): List<String> {

        val result = ArrayList<String>()

        val list = getSharedPreferencesArrayList(context, SharedPreferencesName, SharedPreferencesKey)
        if (list != null) {
            if (list.size > 0) {
                for (`object` in list) {
                    result.add(`object`)
                }
            }
        }

        return result
    }

    fun buildSharedPreferencesArrayIntList(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String): List<Int> {

        val result = ArrayList<Int>()

        val list = getSharedPreferencesArrayList(context, SharedPreferencesName, SharedPreferencesKey)
        if (list != null) {
            if (list.size > 0) {
                for (`object` in list) {

                    try {

                        val convertStringToInteger = Integer.parseInt(`object`)
                        result.add(convertStringToInteger)

                    } catch (error: Exception) {


                    }

                }

            }
        }

        return result
    }

    fun buildSharedPreferencesIntegerArrayList(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String): ArrayList<Int> {

        var result = ArrayList<Int>()
        val integerList = ArrayList<Int>()
        val list = getSharedPreferencesArrayList(context, SharedPreferencesName, SharedPreferencesKey)
        if (list != null) {
            if (list.size > 0) {

                for (`object` in list) {

                    try {

                        val convertStringToInteger = Integer.parseInt(`object`)
                        integerList.add(convertStringToInteger)

                    } catch (error: Exception) {


                    }

                }

                result = integerList

            }
        }

        return result
    }

    fun findValueSharedPreferencesArrayList(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String,
        id: String): Boolean {

        var result = false

        val list = getSharedPreferencesArrayList(context, SharedPreferencesName, SharedPreferencesKey)
        if (list != null) {
            if (list.size > 0) {

                for (`object` in list) {

                    if (`object` == id) {
                        Log.e("ArrayList", "findValueSharedPreferencesArrayList: $`object`/$id")
                        result = true
                    }
                }

            }
        }

        return result
    }

    fun saveSharedPreferencesArrayListByKeyAndValue(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String,
        list: ArrayList<String>
    ): Boolean {

        var result = false

        val settings = context.getSharedPreferences(SharedPreferencesName, 0)
        val editor = settings.edit()
        val gson = Gson()
        val json = gson.toJson(list)
        editor.putString(SharedPreferencesKey, json)

        if (editor.commit()) {
            result = true
        }

        return result
    }
    //endregion SharedPref ArrayList

    //region SharedPref Long
    fun getSharedPreferencesLongValueByKey(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String): Long {

        var result: Long = 0

        try {

            val settings = context.getSharedPreferences(SharedPreferencesName, 0)
            result = settings.getLong(SharedPreferencesKey, 0)

        } catch (error: Exception) {

            Log.e("ApplicationHelper", error.message.toString())

        }

        return result
    }

    fun setSharedPreferencesLongByKeyAndValue(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String,
        SharedPreferencesValue: Long
    ): Boolean {
        var result = false

        val oldValue = getSharedPreferencesLongValueByKey(context, SharedPreferencesName, SharedPreferencesKey)

        val settings = context.getSharedPreferences(SharedPreferencesName, 0)
        val editor = settings.edit()
        editor.putLong(SharedPreferencesKey, SharedPreferencesValue)
        if (editor.commit()) {
            Log.e(
                "ApplicationHelper",
                "key(" + SharedPreferencesKey + ")/value(" + SharedPreferencesValue + ")new(" + getSharedPreferencesLongValueByKey(
                    context,
                    SharedPreferencesName,
                    SharedPreferencesKey
                ) + ")/old(" + oldValue + ")"
            )
            result = true
        }

        return result

    }
    //endregion SharedPref Long

    //region SharedPref Float
    fun getSharedPreferencesFloatValueByKey(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String): Float {

        var result: Float = 0f

        try {

            val settings = context.getSharedPreferences(SharedPreferencesName, 0)
            result = settings.getFloat(SharedPreferencesKey, 0f)

        } catch (error: Exception) {

            Log.e("ApplicationHelper", error.message.toString())

        }

        return result
    }

    fun setSharedPreferencesFloatByKeyAndValue(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String,
        SharedPreferencesValue: Float
    ): Boolean {
        var result = false
        val settings = context.getSharedPreferences(SharedPreferencesName, 0)
        val editor = settings.edit()
        editor.putFloat(SharedPreferencesKey, SharedPreferencesValue)
        if (editor.commit()) {
            result = true
        }
        return result
    }
    //endregion SharedPref Float

    //region SharedPref HashMap
    /*
        How to get HashMap<> data
        var hash_map_data = bhapps.utilitools.preferences.kotlin.shared.GetSetSharedPreferences.getSharedPreferencesHashMapValueByKey(
            requireContext(),
            APPTAG,
            "my_shared_pref_key"
        )
        if(hash_map_data !=null){
            for (hash_map_data_item in hash_map_data) {
                //key - hash_map_data_item.key
                //value - hash_map_data_item.value
            }
        }

    */
    fun getSharedPreferencesHashMapValueByKey(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String): HashMap<Any, Any>? {
        var result: HashMap<Any, Any>? = null
        try {
            var settings = context.getSharedPreferences(SharedPreferencesName, 0)
            var settingsReturnedValue = settings.getString(SharedPreferencesKey, "").toString()
            val listType = object : TypeToken<HashMap<Any, Any>>() {}.type
            if(settingsReturnedValue !=null) {
                result = Gson().fromJson(settings.getString(SharedPreferencesKey, ""), listType)
            }
        } catch (error: Exception) {
            Log.e("ApplicationHelper", error.message.toString())
        }
        return result
    }

    /*
        How to save HashMap<>
        //create hashmap keys with values
        var hashMap = HashMap<Any, Any>()
        hashMap.put("key", "value")
        hashMap.put("key", "value")
        hashMap.put("key", "value")

        //save and check returned success boolean
        if(bhapps.utilitools.preferences.kotlin.shared.GetSetSharedPreferences.setSharedPreferencesHashMapByKeyAndValue(
                requireContext(),
                APPTAG,
                "my_shared_pref_key",
                hashMap
            )){
            Log.e("HashMapByKeyAndValue", "setSharedPreferencesHashMapByKeyAndValue(my_shared_pref_key): saved!")
        }else{
            Log.e("HashMapByKeyAndValue", "setSharedPreferencesHashMapByKeyAndValue(my_shared_pref_key): failed!")
        }

    */
    fun setSharedPreferencesHashMapByKeyAndValue(
        context: Context,
        SharedPreferencesName: String,
        SharedPreferencesKey: String,
        SharedPreferencesValue: HashMap<Any, Any>
    ): Boolean {
        var result = false

        try {
            val settings = context.getSharedPreferences(SharedPreferencesName, 0)
            val editor = settings.edit()
            val SharedPreferencesValueHashMapToGsonString: String  = Gson().toJson(SharedPreferencesValue)
            editor.putString(SharedPreferencesKey, SharedPreferencesValueHashMapToGsonString)
            if (editor.commit()) {
                result = true
            }
        } catch (error: Exception) {
            Log.e("ApplicationHelper", error.message.toString())
        }

        return result

    }
    //endregion SharedPref HashMap
    
}
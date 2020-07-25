/*
     *
     * BH Apps
     * version 0.0.2
     * Methods for observing Shared Preferences changes
     * bhapps.utilitools.kotlin.helpers.preferences.shared
     *
*/

package bhapps.utilitools.preferences.kotlin.shared

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/*

    *
        * BH Apps
        * version 0.0.2
        * Methods for observing Shared Preferences changes
        * bhapps.utilitools.kotlin.helpers.preferences.shared
        *
        *
        * SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        * val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val liveSharedPreferences = LiveSharedPreferences(preferences)

        liveSharedPreferences.getLiveSharedPreferencesString("exampleString", "default").observe(this, Observer<String> { value ->
            Log.d(TAG, value)
        })

        liveSharedPreferences.getLiveSharedPreferencesInt("exampleInt", 0).observe(this, Observer<Int> { value ->
            Log.d(TAG, value.toString())
        })

        liveSharedPreferences.getLiveSharedPreferencesBoolean("exampleBoolean", false).observe(this, Observer<Boolean> { value ->
            Log.d(TAG, value.toString())
        })

        * //Additionally, you can also observe multiple preferences which are same type in the one Observer object.
        liveSharedPreferences.listenMultipleLiveSharedPreferences(listOf("bool1", "bool2", "bool3"), false).observe(this, Observer {

        })

        //For listening only updates without values (They can be different type of preferences)
        liveSharedPreferences.listenUpdatesOnly(listOf("pref1", "pref2", "pref3")).observe(this, Observer { key ->
            Log.d(TAG, "$key updated!")
        })

 */

@Suppress("UNCHECKED_CAST")
class LivePreference<T> constructor(private val updates: Observable<String>,
                                    private val preferences: SharedPreferences,
                                    private val key: String,
                                    private val defaultValue: T) : MutableLiveData<T>() {

    private var disposable: Disposable? = null

    override fun onActive() {
        super.onActive()
        value = (preferences.all[key] as T) ?: defaultValue

        disposable = updates.filter { t -> t == key }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object: DisposableObserver<String>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: String) {
                        postValue((preferences.all[t] as T) ?: defaultValue)
                    }

                    override fun onError(e: Throwable) {

                    }
                })
    }

    override fun onInactive() {
        super.onInactive()
        disposable?.dispose()
    }
}

class LiveSharedPreferences constructor(private val preferences: SharedPreferences) {

    private val publisher = PublishSubject.create<String>()
    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, key -> publisher.onNext(key) }

    /**
     * Detect subscription and dispose events
     */
    private val updates = publisher.doOnSubscribe {
        preferences.registerOnSharedPreferenceChangeListener(listener)
    }.doOnDispose {
        if (!publisher.hasObservers())
            preferences.unregisterOnSharedPreferenceChangeListener(listener)
    }

    fun getLiveSharedPreferences(): SharedPreferences {
        return preferences
    }

    fun getLiveSharedPreferencesString(key: String, defaultValue: String): LivePreference<String> {
        return LivePreference(updates, preferences, key, defaultValue)
    }

    fun getLiveSharedPreferencesInt(key: String, defaultValue: Int): LivePreference<Int> {
        return LivePreference(updates, preferences, key, defaultValue)
    }

    fun getLiveSharedPreferencesBoolean(key: String, defaultValue: Boolean): LivePreference<Boolean> {
        return LivePreference(updates, preferences, key, defaultValue)
    }

    fun getLiveSharedPreferencesFloat(key: String, defaultValue: Float): LivePreference<Float> {
        return LivePreference(updates, preferences, key, defaultValue)
    }

    fun getLiveSharedPreferencesLong(key: String, defaultValue: Long): LivePreference<Long> {
        return LivePreference(updates, preferences, key, defaultValue)
    }

    fun getLiveSharedPreferencesStringSet(key: String, defaultValue: Set<String>): LivePreference<Set<String>> {
        return LivePreference(updates, preferences, key, defaultValue)
    }

    fun <T> listenMultipleLiveSharedPreferences(keys: List<String>, defaultValue: T): LiveSharedPreferencesMultiPreference<T> {
        return LiveSharedPreferencesMultiPreference(updates, preferences, keys, defaultValue)
    }
}

@Suppress("UNCHECKED_CAST")
class LiveSharedPreferencesMultiPreference<T> constructor(private val updates: Observable<String>,
                                     private val preferences: SharedPreferences,
                                     private val keys: List<String>,
                                     private val defaultValue: T) : MutableLiveData<Map<String, T>>() {

    private var disposable: Disposable? = null
    private val values = mutableMapOf<String, T>()

    init {
        for (key in keys)
            values[key] = preferences.all[key] as T ?: defaultValue
    }

    override fun onActive() {
        super.onActive()
        value = values

        disposable = updates.filter { t -> keys.contains(t) }.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(object: DisposableObserver<String>() {
                    override fun onComplete() {

                    }

                    override fun onNext(t: String) {
                        values[t] = preferences.all[t] as T ?: defaultValue
                        postValue(values)
                    }

                    override fun onError(e: Throwable) {

                    }
                })
    }

    override fun onInactive() {
        super.onInactive()
        disposable?.dispose()
    }
}
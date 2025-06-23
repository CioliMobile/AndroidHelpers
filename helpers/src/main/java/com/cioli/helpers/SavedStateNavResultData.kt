package com.cioli.helpers

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SavedStateNavResultData(
	val navController: NavController?
) {
	inline fun <reified T> getResultForKey(key: String): Flow<T?> =
		navController
			?.currentBackStackEntry
			?.savedStateHandle
			?.getStateFlow<String?>(key, null)
			?.map {
				it?.let {
					Json.decodeFromString<T>(it)
				}
			} ?: flowOf(null)
}

val LocalNavResultData = compositionLocalOf { SavedStateNavResultData(null) }

/**
 * Composable wrapper of [NavController.getResultAsFlow]
 */
@Composable
inline fun <reified T> NavResultView(
	key: String,
	content: @Composable (T?) -> Unit,
) {
	val result = LocalNavResultData.current.getResultForKey<T>(key).collectAsStateWithLifecycle(null, LocalLifecycleOwner.current)
	content(
		result.value
	)
}

inline fun <reified T> getGenericNavType(isNullable: Boolean = false): NavType<T> =
	object : NavType<T>(isNullable) {
		override fun get(
			bundle: Bundle,
			key: String
		): T? =
			bundle.getString(key)?.let {
				parseValue(it)
			}

		override fun parseValue(value: String): T = Json.decodeFromString(value)

		override fun serializeAsValue(value: T): String  = Json.encodeToString(value)

		override fun put(
			bundle: Bundle,
			key: String,
			value: T,
		) {
			bundle.putString(key, serializeAsValue(value))
		}
	}


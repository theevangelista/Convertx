package io.github.joaoevangelista.convertx.history

import io.realm.Realm
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Date

/**
 * Represent a entry of conversion happened at a time
 *
 * @author Joao Pedro Evangelista
 */
open class Entry(
  open var fromUnit: String? = null,
  open var toUnit: String? = null,
  open var input: Double? = null,
  open var output: Double? = null,
  @PrimaryKey open var timestamp: Long = Date().time
) : RealmObject()

fun createEntryAsync(entry: Entry) {
  Realm.getDefaultInstance().executeTransactionAsync { transaction ->
    transaction.copyToRealm(entry)
  }
}

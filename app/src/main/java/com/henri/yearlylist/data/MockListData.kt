package com.henri.yearlylist.data

import com.henri.yearlylist.data.room.Item
import java.util.*

object MockListData {
    val list = mutableListOf<Item>(
        Item("Pääsiäisvaellus", "Muonioon pääsiäiseksi vaeltamaan", 1),
        Item("Valmistuminen", "Valmistut koulusta toukokuun lopussa", 1)
    )

}
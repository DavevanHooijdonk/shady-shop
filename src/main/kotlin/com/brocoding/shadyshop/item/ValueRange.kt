package com.brocoding.shadyshop.item

import com.brocoding.shadyshop.item.DiceSize.NONE
import javax.persistence.Embeddable
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id

/**
 * Created by Dave van Hooijdonk on 18-6-2018.
 */
@Entity
data class ValueRange(val start: Int,
                      val end: Int,
                      val value: Value) {

    @Id
    val id: String = this.toString()

    override fun toString(): String {
        val (size, dice, base) = value
        return "Range: $start - $end Value: $size$dice + $base"
    }
}

@Embeddable
data class Value(val numberOfDice: Int? = 0,
                 @Enumerated(EnumType.STRING)
                 val diceSize: DiceSize? = NONE,
                 val base: Int? = 0)

enum class DiceSize(val max: Int) {
    NONE(0),
    D4(4),
    D6(6),
    D8(8),
    D10(10),
    D12(12),
    D20(20)
}
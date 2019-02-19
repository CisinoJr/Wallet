package br.com.cisinojr.wallet.domain

import java.math.BigDecimal

/**
 *
 * Created by cisino.gomes on 2/18/2019
 *
 */
data class Currency(
    val id: Int,
    val balance: BigDecimal
)
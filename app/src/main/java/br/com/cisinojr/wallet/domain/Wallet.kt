package br.com.cisinojr.wallet.domain

import java.math.BigDecimal

/**
 *
 * Created by cisino.gomes on 2/18/2019
 *
 */
data class Wallet (
    val id: Int,
    val securityPassword: String,
    val currency: Currency,
    val bitcoin: Bitcoin,
    val brita: Brita
)
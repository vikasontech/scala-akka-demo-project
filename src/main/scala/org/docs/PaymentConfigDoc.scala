package org.docs

import java.lang.annotation.Documented


@Documented
case class PaymentConfigDoc(_id: String,
                            totalAmount: BigDecimal,
                            bankContribution: BigDecimal,
                            ownContribution: BigDecimal)




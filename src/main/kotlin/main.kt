import jp.co.soramitsu.iroha.java.IrohaAPI
import jp.co.soramitsu.iroha.java.Query
import jp.co.soramitsu.iroha.java.Transaction
import jp.co.soramitsu.iroha.java.Utils
import java.math.BigDecimal

val adminKeys = Utils.parseHexKeypair(
    "313a07e6384776ed95447710d15e59148473ccfc052a681317a72a69f2a49910",
    "f101537e319568c765b2cc89698325604991dca57b9716b58016b253506cab70"
)

val userKeys = Utils.parseHexKeypair(
    "313a07e6384776ed95447710d15e59148473ccfc052a681317a72a69f2a49910",
    "f101537e319568c765b2cc89698325604991dca57b9716b58016b253506cab70"
)

val adminId = "admin@test"
val userId = "test@test"
val assetId = "coin#test"

fun main(args: Array<String>) {
    val irohaAPI = IrohaAPI("localhost", 50051)
    val tx = Transaction.builder(adminId)
        .addAssetQuantity(assetId, BigDecimal.TEN)
        .sign(adminKeys)
        .build()


    irohaAPI.transactionSync(tx)
    repeat(10) {
        println(irohaAPI.txStatusSync(Utils.hash(tx)))
        Thread.sleep(1000)
    }

    val resp = irohaAPI.query(Query.builder(adminId, 1).getAccountAssets(adminId).buildSigned(adminKeys))
    println(resp)

}
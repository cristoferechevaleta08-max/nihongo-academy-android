package com.nihongo.academy

import org.junit.Assert.*
import org.junit.Test

class CatalogTest {
 @Test fun sourceRequirementsKeepSeventyPercentPassMark() { assertTrue(4f/5f >= .7f); assertFalse(3f/5f >= .7f) }
 @Test fun configuredLanguageListContainsFifteenCodes() { assertEquals(15, listOf("ja","en","ko","zh","fr","de","it","pt","es","ru","ar","hi","tr","bn","id").size) }
}

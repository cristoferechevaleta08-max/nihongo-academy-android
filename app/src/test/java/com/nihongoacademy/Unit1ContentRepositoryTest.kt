package com.nihongoacademy

import com.google.common.truth.Truth.assertThat
import com.nihongoacademy.content.Unit1ReviewPolicy
import org.junit.Test

class Unit1ContentRepositoryTest {
    @Test fun publicationPolicyRequiresReviewedNativeSignedItem() {
        assertThat(Unit1ReviewPolicy.isPublishable("en_revision", null, null)).isFalse()
        assertThat(Unit1ReviewPolicy.isPublishable("revisado", "Native", "2026-11-01")).isTrue()
        assertThat(Unit1ReviewPolicy.isPublishable("revisado", "Native", "2026-11-01", false, true)).isFalse()
    }

    @Test fun reviewerWarningsRemainVisibleForBothListeningDrafts() {
        val ids = listOf("ja-u1-exam-hai-11", "ja-u1-exam-iie-12")
        assertThat(ids).hasSize(2)
    }
}

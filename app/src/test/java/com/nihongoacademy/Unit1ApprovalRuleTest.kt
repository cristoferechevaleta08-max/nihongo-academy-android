package com.nihongoacademy

import com.google.common.truth.Truth.assertThat
import com.nihongoacademy.content.Unit1ReviewPolicy
import org.junit.Test

class Unit1ApprovalRuleTest {
    @Test fun nineOfThirteenFails() { assertThat(Unit1ReviewPolicy.passes(9,mapOf("lectura" to 4,"escritura" to 3,"produccion" to 2,"vocabulario" to 2,"auditiva" to 2))).isFalse() }
    @Test fun tenPointsWithAnySkillBelowSixtyPercentFails() { assertThat(Unit1ReviewPolicy.passes(10,mapOf("lectura" to 3,"escritura" to 2,"produccion" to 2,"vocabulario" to 2,"auditiva" to 1))).isFalse() }
    @Test fun elevenPointsAtAllRoundedSkillMinimumsPasses() { assertThat(Unit1ReviewPolicy.passes(11,mapOf("lectura" to 3,"escritura" to 2,"produccion" to 2,"vocabulario" to 2,"auditiva" to 2))).isTrue() }
    @Test fun tenPointsCannotMeetAllRoundedSkillMinimums() { assertThat(Unit1ReviewPolicy.passes(10,mapOf("lectura" to 3,"escritura" to 2,"produccion" to 2,"vocabulario" to 2,"auditiva" to 1))).isFalse() }
    @Test fun publishRequiresNativeReviewerDateAndListeningAudio() {
        assertThat(Unit1ReviewPolicy.isPublishable("revisado","Native reviewer","2026-11-01")).isTrue()
        assertThat(Unit1ReviewPolicy.isPublishable("revisado",null,"2026-11-01")).isFalse()
        assertThat(Unit1ReviewPolicy.isPublishable("revisado","Native reviewer",null)).isFalse()
        assertThat(Unit1ReviewPolicy.isPublishable("revisado","Native reviewer","2026-11-01",false,true)).isFalse()
    }
    @Test fun editingReviewedItemInvalidatesAndLogsReview() {
        val item=Unit1ReviewPolicy.ReviewItem("id","revisado","native","2026-11-01")
        val (updated,event)=Unit1ReviewPolicy.invalidateAfterEdit(item,"2026-11-02T10:00:00Z","editor","corrección")
        assertThat(updated.status).isEqualTo("en_revision"); assertThat(updated.reviewerNative).isNull(); assertThat(updated.reviewDate).isNull()
        assertThat(event.previousStatus).isEqualTo("revisado"); assertThat(event.newStatus).isEqualTo("en_revision"); assertThat(event.reason).isEqualTo("corrección")
    }
}

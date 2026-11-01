package com.nihongoacademy.content

object Unit1ReviewPolicy {
    fun isPublishable(status: String, reviewerNative: String?, reviewDate: String?, audioAvailable: Boolean = true, listening: Boolean = false): Boolean =
        status == "revisado" && !reviewerNative.isNullOrBlank() && !reviewDate.isNullOrBlank() && (!listening || audioAvailable)

    fun officialItemIds(items: List<ReviewItem>): List<String> =
        items.filter { isPublishable(it.status, it.reviewerNative, it.reviewDate, it.audioAvailable, it.listening) }.map { it.id }

    /** Regla pura: mínimo global y ceil(60%) en cada competencia. */
    fun passes(totalPoints: Int, pointsByCompetency: Map<String, Int>): Boolean {
        if (totalPoints < 10 || totalPoints > 13) return false
        val minimums = mapOf("lectura" to 3, "escritura" to 2, "produccion" to 2, "vocabulario" to 2, "auditiva" to 2)
        return minimums.all { (skill, minimum) -> (pointsByCompetency[skill] ?: 0) >= minimum }
    }

    data class ReviewItem(val id:String,val status:String,val reviewerNative:String?,val reviewDate:String?,val audioAvailable:Boolean=true,val listening:Boolean=false)
    data class ReviewChange(val itemId:String,val changedAt:String,val changedBy:String,val reason:String,val previousStatus:String,val newStatus:String="en_revision")

    fun invalidateAfterEdit(item: ReviewItem, changedAt:String, changedBy:String, reason:String): Pair<ReviewItem,ReviewChange> {
        val updated=item.copy(status="en_revision",reviewerNative=null,reviewDate=null)
        return updated to ReviewChange(item.id,changedAt,changedBy,reason,item.status)
    }
}

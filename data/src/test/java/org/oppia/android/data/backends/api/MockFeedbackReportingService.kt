package org.oppia.android.data.backends.api

import org.oppia.android.app.model.FeedbackReport
import org.oppia.android.data.backends.gae.api.FeedbackReportingService
import retrofit2.Call
import retrofit2.mock.BehaviorDelegate

/** Mock FeedbackReportingService to check that the service is properly requested. */
class MockFeedbackReportingService(
  private val delegate: BehaviorDelegate<FeedbackReportingService>
) : FeedbackReportingService {
  override fun postFeedbackReport(report: FeedbackReport): Call<Unit> {
    return delegate.returningResponse(kotlin.Unit).postFeedbackReport(report)
  }
}
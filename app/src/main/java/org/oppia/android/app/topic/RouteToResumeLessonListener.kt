package org.oppia.android.app.topic

/** Listener for when an activity should route to a [ResumeLessonActivity]. */
interface RouteToResumeLessonListener {
  /** Called selects an exploration that can be resumed. */
  fun routeToResumeLesson(
    internalProfileId: Int,
    topicId: String,
    storyId: String,
    explorationId: String,
    backflowScreen: Int?
  )
}
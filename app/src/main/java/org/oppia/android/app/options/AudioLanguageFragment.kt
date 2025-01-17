package org.oppia.android.app.options

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.oppia.android.app.fragment.FragmentComponentImpl
import org.oppia.android.app.fragment.InjectableFragment
import org.oppia.android.util.extensions.getStringFromBundle
import javax.inject.Inject

private const val AUDIO_LANGUAGE_PREFERENCE_TITLE_ARGUMENT_KEY =
  "AudioLanguageFragment.audio_language_preference_title"
private const val AUDIO_LANGUAGE_PREFERENCE_SUMMARY_VALUE_ARGUMENT_KEY =
  "AudioLanguageFragment.audio_language_preference_summary_value"
private const val SELECTED_AUDIO_LANGUAGE_SAVED_KEY =
  "AudioLanguageFragment.selected_audio_language"

/** The fragment to change the default audio language of the app. */
class AudioLanguageFragment :
  InjectableFragment(),
  LanguageRadioButtonListener {

  @Inject
  lateinit var audioLanguageFragmentPresenter: AudioLanguageFragmentPresenter

  companion object {
    fun newInstance(prefsKey: String, prefsSummaryValue: String): AudioLanguageFragment {
      val args = Bundle()
      args.putString(AUDIO_LANGUAGE_PREFERENCE_TITLE_ARGUMENT_KEY, prefsKey)
      args.putString(AUDIO_LANGUAGE_PREFERENCE_SUMMARY_VALUE_ARGUMENT_KEY, prefsSummaryValue)
      val fragment = AudioLanguageFragment()
      fragment.arguments = args
      return fragment
    }
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    (fragmentComponent as FragmentComponentImpl).inject(this)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    val args =
      checkNotNull(arguments) { "Expected arguments to be passed to AudioLanguageFragment" }
    val prefsKey = args.getStringFromBundle(AUDIO_LANGUAGE_PREFERENCE_TITLE_ARGUMENT_KEY)
    val audioLanguageDefaultSummary = checkNotNull(
      args.getStringFromBundle(AUDIO_LANGUAGE_PREFERENCE_SUMMARY_VALUE_ARGUMENT_KEY)
    )
    val prefsSummaryValue = if (savedInstanceState == null) {
      audioLanguageDefaultSummary
    } else {
      savedInstanceState.get(SELECTED_AUDIO_LANGUAGE_SAVED_KEY) as? String
        ?: audioLanguageDefaultSummary
    }
    return audioLanguageFragmentPresenter.handleOnCreateView(
      inflater,
      container,
      prefsKey!!,
      prefsSummaryValue
    )
  }

  override fun onSaveInstanceState(outState: Bundle) {
    super.onSaveInstanceState(outState)
    outState.putString(
      SELECTED_AUDIO_LANGUAGE_SAVED_KEY,
      audioLanguageFragmentPresenter.getLanguageSelected()
    )
  }

  override fun onLanguageSelected(selectedLanguage: String) {
    audioLanguageFragmentPresenter.onLanguageSelected(selectedLanguage)
  }
}

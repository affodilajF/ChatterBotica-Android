package com.example.chatterboticaapp.utils


//@Composable
//actual fun rememberTextToSpeechOrNull(requestedEngine: TextToSpeechEngine): TextToSpeechInstance? {
//    val context = LocalContext.current.applicationContext
//
//    var textToSpeech by remember { mutableStateOf<TextToSpeechInstance?>(null) }
//
//    LaunchedEffect(Unit) {
//        textToSpeech = TextToSpeechFactory(context, requestedEngine).createOrNull()
//    }
//
//    DisposableEffect(Unit) {
//        onDispose {
//            textToSpeech?.close()
//            textToSpeech = null
//        }
//    }
//
//    return textToSpeech
//}
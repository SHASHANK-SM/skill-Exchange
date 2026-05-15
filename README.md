# Skill-Exchange

Skill-Exchange is a Kotlin + XML Android project for a rural self-employment barter platform where technicians exchange skills, services, and time-based skill points.

## Built With

- Kotlin
- XML layouts with ViewBinding
- Material Design Components
- Firebase Firestore
- Firebase Auth anonymous sign-in
- RecyclerView and CardView
- Bottom Navigation with Fragments

## Core Logic

- `1 hour = 1 skill point`
- Need posts can be filtered by required skill.
- Swap offers move through pending, accepted, and completed states.
- Trust score increases only after both users confirm a completed swap.

## Firebase Setup

The project includes a placeholder `app/google-services.json` so the structure opens cleanly in Android Studio. Replace it with your real Firebase Android config for package:

```text
com.rural.skill_exchange
```

Create these Firestore collections:

- `profiles`
- `needs`
- `offers`
- `negotiations`

The app also seeds friendly sample content locally so screens are useful before Firestore has data.

## Open In Android Studio

1. Open this folder in Android Studio.
2. Sync Gradle.
3. Replace `app/google-services.json` with your real Firebase file.
4. Run the app on an emulator or physical device.

## Main Files

- `app/src/main/java/com/rural/skill_exchange/MainActivity.kt`
- `app/src/main/java/com/rural/skill_exchange/data/FirebaseRepository.kt`
- `app/src/main/java/com/rural/skill_exchange/ui/home/HomeFragment.kt`
- `app/src/main/java/com/rural/skill_exchange/ui/needs/NeedsFragment.kt`
- `app/src/main/java/com/rural/skill_exchange/ui/offers/OffersFragment.kt`
- `app/src/main/java/com/rural/skill_exchange/ui/profile/ProfileFragment.kt`
- `app/src/main/java/com/rural/skill_exchange/ui/chat/NegotiationFragment.kt`

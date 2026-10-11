# BAuto — Test Android Auto su Windows senza Android Studio

**Ultima verifica:** 2026-10-11. **Dispositivo di test noto:** Android 13 (API 33); modello da confermare.
**Ambito:** test del prototipo APK creato da GitHub Actions, non build Android locale.

## Scelta

Non è necessario installare Android Studio perché GitHub Actions genera già l'APK.
Sono sufficienti:
- JDK 17+ per SDK Manager;
- Android SDK Command-line Tools (per installare gli altri pacchetti);
- SDK Platform Tools (ADB);
- Android Auto Desktop Head Unit (DHU);
- smartphone Android 13 con Google Play/Android Auto aggiornati, USB debugging abilitato e cavo USB dati.

L'installazione standard va in %LOCALAPPDATA%\Android\Sdk. Non occorrono il grande IDE, l'emulatore smartphone o SDK Platform 36 per installare ed eseguire un APK precompilato.

## Preparazione

1. Leggi e accetta i termini ufficiali Android SDK: https://developer.android.com/studio
2. Verifica JDK: PowerShell, comando \`java -version\`. Se mancante, installa JDK 17, ad esempio via \`winget install EclipseAdoptium.Temurin.17.JDK\`, poi riapri il terminale.
3. Clona la PR del repository oppure scarica i due script dalla cartella \`scripts/windows\` di GitHub in una cartella locale.
4. Da PowerShell nella root repo:

   \`\`\`powershell
   powershell -NoProfile -ExecutionPolicy Bypass -File .\scripts\windows\install-dhu.ps1
   \`\`\`

   Lo script scarica dal dominio ufficiale Google i Command-line Tools, confronta SHA-256 con quello pubblicato da Google e usa SDK Manager per installare \`platform-tools\` e \`extras;google;auto\`. Le licenze vengono accettate interattivamente, non automaticamente. Controlla il contenuto dello script prima di eseguirlo.

## Recupero APK

1. GitHub Actions: https://github.com/babbubba/bAuto/actions/workflows/android-auto-poi.yml
2. Apri una run **riuscita** del workflow Android Auto POI PoC sul branch \`feature/task-003-poc-aa-001\`.
3. Scarica l'artefatto \`bauto-poi-debug-apk\` e **estrailo** (APK contenuto in un ZIP generato da GitHub Actions: non è uno ZIP del repository).
4. Annota il percorso dell'APK estratto, per esempio \`C:\Users\<utente>\Downloads\bauto\app-debug.apk\`.

## Preparazione Android 13

1. In Impostazioni > Info dispositivo, abilita Opzioni sviluppatore toccando ripetutamente il numero build (il menu può variare secondo OEM).
2. Abilita **Debug USB**.
3. Android Auto > Impostazioni: verifica connessione account Google Play e aggiornamenti; abilita modalità sviluppatore di Android Auto secondo le indicazioni ufficiali.
4. Dal menu sviluppatore Android Auto seleziona **Start head unit server**.
5. Collega lo smartphone al PC con un cavo dati, sbloccalo e autorizza l'impronta RSA del PC quando richiesta.

## Esecuzione

Da PowerShell nella root del repo:

\`\`\`powershell
powershell -NoProfile -ExecutionPolicy Bypass -File .\scripts\windows\start-dhu.ps1 -Apk "C:\Users\<utente>\Downloads\bauto\app-debug.apk"
\`\`\`

Lo script verifica \`adb devices\`, installa il debug APK, configura la porta TCP 5277 e avvia \`desktop-head-unit.exe\`.

Nel DHU apri il launcher Android Auto e cerca **BAuto POI PoC**. I tre POI sono fittizi/dimostrativi, non usare il prototipo per navigare. Se l'app non è visibile, annotare versione Android Auto e logcat; verificare host/categoria e regole di test delle app Car App Library, **senza installare APK con trucchi o aggirare Play policy**.

## Troubleshooting rapido

- \`java\` mancante: installa JDK 17 o configura JAVA_HOME/PATH.
- \`unauthorized\` in adb: sblocca telefono e accetta il dialogo RSA, poi ripeti.
- \`offline\` in adb: scollega/ricollega USB e cambia modalità/cavo.
- Impossibile avviare DHU: controlla che il servizio \`Start head unit server\` sia attivo sul telefono e riprova il forward TCP.
- Package non presente in SDK Manager: esegui \`sdkmanager --list\`; non utilizzare download non ufficiali.
- L'app non compare nel launcher: non confondere installazione APK con autorizzazione host per le categorie Android Auto.

## Riferimenti ufficiali

- https://developer.android.com/studio (command-line tools e licenza)
- https://developer.android.com/studio/command-line/sdkmanager
- https://developer.android.com/tools/releases/platform-tools
- https://developer.android.com/training/cars/testing/dhu
- https://developer.android.com/training/cars/testing

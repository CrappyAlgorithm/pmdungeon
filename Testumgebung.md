---
title:  'Ausarbeitung zum Projektthema PM-Dungeon'
author:
- Sebastian Steinmeyer (sebastian.steinmeyer@fh-bielefeld.de)
...


# Verwendung der Testumgebung

## Maven

Durch die Nutzung der Maven Targets kann die Testsuite manuell jederzeit ausgeführt werden.
Hierzu wird nur ein Terminal, Maven und eine passende JDK Version (hier JDK 11) benötigt.
Alle weiteren Abhängigkeiten werden selbstständig nachgeladen.

Es stehen folgende Maven Targets zur Verfügung:

| Maven Target | abhängige Targets | Beschreibung |
|---|:---:|:---:|
| clean | --- | Löscht alle bereits compilierten und generierten Dateien. |
| pmd:pmd | --- | Führt PMD aus und generiert einen Ergebnisbericht. |
| compile | --- | Compiliert alle Artefakte. |
| test | compile | Ausführen der Junit Testsuite inklusive JaCoCo überwachung. |
| jacoco:report | test | Generiert den JaCoCo Bericht des letzten Testlaufs. |
| package | test, jacoco:report | Erstellen der Projekt Jars und einer Jar inklusive Abhängigkeiten. |
| install | package | Installiert alle Artefakte ins lokale Maven Repository |

## Github Actions

Die Github Actions Pipeline wird bei jedem Push, sowie Pull Request ausgeführt.
Dabei werden alle relevanten Target ausgeführt und die erstellten Berichte und Artefakte zu jedem Lauf zum Download angeboten.
Zudem werden Statusbadges für das Ergebnis des Testlaufs sowie für die Testabdeckung angeboten.

Um die Artefakte herunterzuladen oder weitere Informationen zu den Durchläufen zu erhalten, muss im Github Repository nur auf den Reiter Actions gewechselt werden.
Dort werden dann alle Durchläufe aufgelistet. Wenn man nun einen der Durchläufe auswählt, erscheinen unten auf der Seite die generierten Artefakte zum Download.
Es wird eine jar mit allen Abhängigkeiten, für jedes der 3 Betriebssysteme, sowie die aktuellen Berichte zu JaCoCo und PMD angeboten.

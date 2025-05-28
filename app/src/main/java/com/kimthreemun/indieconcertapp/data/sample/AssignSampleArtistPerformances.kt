// com.kimthreemun.indieconcertapp.data.sample.AssignSampleArtistPerformances.kt
package com.kimthreemun.indieconcertapp.data.sample

fun AssignSampleArtistPerformances() {
    val artist1 = SampleArtists.artist1
    val artist2 = SampleArtists.artist2
    val artist3 = SampleArtists.artist3
    val artist4 = SampleArtists.artist4
    val artist5 = SampleArtists.artist5
    val artist6 = SampleArtists.artist6
    val artist7 = SampleArtists.artist7
    val artist8 = SampleArtists.artist8
    val artist9 = SampleArtists.artist9
    val artist10 = SampleArtists.artist10
    val artist11 = SampleArtists.artist11
    val artist12 = SampleArtists.artist12
    val artist13 = SampleArtists.artist13

    val p1 = SamplePerformances.performance1
    val p2 = SamplePerformances.performance2
    val p3 = SamplePerformances.performance3
    val p4 = SamplePerformances.performance4
    val p5 = SamplePerformances.performance5
    val p6 = SamplePerformances.performance6

    // 🔗 공연에 아티스트 연결
    p1.artists = listOf(artist1, artist2, artist3, artist4)
    p2.artists = listOf(artist1, artist5, artist6, artist7, artist8)
    p3.artists = listOf(artist1, artist9, artist10, artist11)
    p4.artists = listOf(artist1, artist12, artist13)
    p5.artists = listOf(artist1, artist2, artist3, artist4)
    p6.artists = listOf(artist1, artist2, artist3, artist4, artist5, artist6, artist7, artist8, artist9, artist10, artist11, artist12, artist13)

    // 🔗 아티스트에 공연 연결
    artist1.scheduledPerformances = listOf(p1)
    artist1.pastPerformances = listOf(p5, p6)

    artist2.scheduledPerformances = listOf(p1, p3)
    artist2.pastPerformances = listOf(p5, p6)

    artist3.scheduledPerformances = listOf(p1, p3)
    artist3.pastPerformances = listOf(p5, p6)

    artist4.scheduledPerformances = listOf(p1, p3)
    artist4.pastPerformances = listOf(p5, p6)

    artist5.scheduledPerformances = listOf(p2)
    artist5.pastPerformances = listOf(p6)

    artist6.scheduledPerformances = listOf(p2)
    artist6.pastPerformances = listOf(p6)

    artist7.scheduledPerformances = listOf(p2)
    artist7.pastPerformances = listOf(p6)

    artist8.scheduledPerformances = listOf(p2)
    artist8.pastPerformances = listOf(p6)

    artist9.scheduledPerformances = listOf(p3)
    artist9.pastPerformances = listOf(p6)

    artist10.scheduledPerformances = listOf(p3)
    artist10.pastPerformances = listOf(p6)

    artist11.scheduledPerformances = listOf(p3)
    artist11.pastPerformances = listOf(p6)

    artist12.scheduledPerformances = listOf(p4)
    artist12.pastPerformances = listOf(p6)

    artist13.scheduledPerformances = listOf(p4)
    artist13.pastPerformances = listOf(p6)

}

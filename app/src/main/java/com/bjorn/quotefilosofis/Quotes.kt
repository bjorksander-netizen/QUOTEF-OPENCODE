package com.bjorn.quotefilosofis

data class Quote(
    val textId: String,
    val textEn: String,
    val author: String,
    val school: String
)

fun Quote.displayText(lang: String) = if (lang == "en") textEn else textId

fun schoolLabel(school: String, lang: String) = when (school) {
    "Optimisme"        -> if (lang == "en") "Optimism"        else "Optimisme"
    "Stoisisme"        -> if (lang == "en") "Stoicism"        else "Stoisisme"
    "Nihilisme"        -> if (lang == "en") "Nihilism"        else "Nihilisme"
    "Eksistensialisme" -> if (lang == "en") "Existentialism"  else "Eksistensialisme"
    "Absurdisme"       -> if (lang == "en") "Absurdism"       else "Absurdisme"
    "Timur"            -> if (lang == "en") "Eastern"         else "Timur"
    else               -> school
}

val ALL_QUOTES = listOf(
    // ── OPTIMISME ─────────────────────────────────────────────────────────────
    Quote(
        "Segalanya berakhir dengan baik pada akhirnya. Jika belum baik, itu belum akhirnya.",
        "Everything will be okay in the end. If it's not okay, it's not the end.",
        "Oscar Wilde", "Optimisme"
    ),
    Quote(
        "Optimisme adalah keyakinan yang mengarah pada pencapaian. Tidak ada yang bisa dilakukan tanpa harapan.",
        "Optimism is the faith that leads to achievement. Nothing can be done without hope.",
        "Helen Keller", "Optimisme"
    ),
    Quote(
        "Di tengah setiap kesulitan tersimpan kesempatan.",
        "In the middle of every difficulty lies opportunity.",
        "Albert Einstein", "Optimisme"
    ),
    Quote(
        "Kita harus menerima kekecewaan yang terbatas, tapi tidak pernah kehilangan harapan yang tak terbatas.",
        "We must accept finite disappointment, but never lose infinite hope.",
        "Martin Luther King Jr.", "Optimisme"
    ),
    Quote(
        "Masa depan adalah milik mereka yang percaya akan keindahan impian mereka.",
        "The future belongs to those who believe in the beauty of their dreams.",
        "Eleanor Roosevelt", "Optimisme"
    ),
    Quote(
        "Tetaplah berharap. Karena bahkan matahari pun terbenam di malam hari dan terbit lagi di pagi hari.",
        "Keep hope alive. For even the sun sets at night and rises again in the morning.",
        "Rumi", "Optimisme"
    ),

    // ── STOISISME ─────────────────────────────────────────────────────────────
    Quote(
        "Hambatan bagi tindakan justru mendorong tindakan. Apa yang menghalangi, menjadi jalannya.",
        "The impediment to action advances action. What stands in the way becomes the way.",
        "Marcus Aurelius", "Stoisisme"
    ),
    Quote(
        "Kamu memiliki kuasa atas pikiranmu, bukan peristiwa luar. Sadari ini, dan kamu akan menemukan kekuatan.",
        "You have power over your mind, not outside events. Realize this, and you will find strength.",
        "Marcus Aurelius", "Stoisisme"
    ),
    Quote(
        "Bukan apa yang terjadi padamu, tapi bagaimana kamu bereaksi terhadapnya yang penting.",
        "It's not what happens to you, but how you react to it that matters.",
        "Epiktetos", "Stoisisme"
    ),
    Quote(
        "Manfaatkan sebaik-baiknya apa yang ada dalam kekuasaanmu, dan terima sisanya sebagaimana adanya.",
        "Make the best use of what is in your power, and take the rest as it happens.",
        "Epiktetos", "Stoisisme"
    ),
    Quote(
        "Kita lebih sering menderita dalam imajinasi daripada dalam kenyataan.",
        "We suffer more often in imagination than in reality.",
        "Seneca", "Stoisisme"
    ),
    Quote(
        "Keberuntungan adalah apa yang terjadi ketika persiapan bertemu kesempatan.",
        "Luck is what happens when preparation meets opportunity.",
        "Seneca", "Stoisisme"
    ),
    Quote(
        "Jangan membuang-buang waktu berdebat tentang seperti apa manusia yang baik itu. Jadilah itu.",
        "Waste no more time arguing about what a good man should be. Be one.",
        "Marcus Aurelius", "Stoisisme"
    ),

    // ── NIHILISME ─────────────────────────────────────────────────────────────
    Quote(
        "Tuhan sudah mati. Tuhan tetap mati. Dan kita yang telah membunuhnya.",
        "God is dead. God remains dead. And we have killed him.",
        "Friedrich Nietzsche", "Nihilisme"
    ),
    Quote(
        "Tanpa musik, hidup akan menjadi sebuah kesalahan.",
        "Without music, life would be a mistake.",
        "Friedrich Nietzsche", "Nihilisme"
    ),
    Quote(
        "Yang tidak membunuhmu akan membuatmu lebih kuat.",
        "That which does not kill us, makes us stronger.",
        "Friedrich Nietzsche", "Nihilisme"
    ),
    Quote(
        "Dia yang punya alasan untuk hidup dapat menanggung hampir segalanya.",
        "He who has a why to live can bear almost any how.",
        "Friedrich Nietzsche", "Nihilisme"
    ),
    Quote(
        "Tidak ada fakta, hanya interpretasi.",
        "There are no facts, only interpretations.",
        "Friedrich Nietzsche", "Nihilisme"
    ),
    Quote(
        "Untuk hidup adalah menderita, untuk bertahan adalah menemukan makna dalam penderitaan.",
        "To live is to suffer, to survive is to find some meaning in the suffering.",
        "Friedrich Nietzsche", "Nihilisme"
    ),

    // ── EKSISTENSIALISME ──────────────────────────────────────────────────────
    Quote(
        "Eksistensi mendahului esensi.",
        "Existence precedes essence.",
        "Jean-Paul Sartre", "Eksistensialisme"
    ),
    Quote(
        "Manusia dikutuk untuk menjadi bebas.",
        "Man is condemned to be free.",
        "Jean-Paul Sartre", "Eksistensialisme"
    ),
    Quote(
        "Neraka adalah orang lain.",
        "Hell is other people.",
        "Jean-Paul Sartre", "Eksistensialisme"
    ),
    Quote(
        "Kamu tidak akan pernah bahagia jika terus mencari apa itu kebahagiaan.",
        "You will never be happy if you continue to search for what happiness consists of.",
        "Albert Camus", "Eksistensialisme"
    ),
    Quote(
        "Seseorang tidak dilahirkan sebagai wanita, melainkan menjadi seorang wanita.",
        "One is not born, but rather becomes, a woman.",
        "Simone de Beauvoir", "Eksistensialisme"
    ),
    Quote(
        "Hidup hanya bisa dipahami ke belakang, tapi harus dijalani ke depan.",
        "Life can only be understood backwards, but it must be lived forwards.",
        "Søren Kierkegaard", "Eksistensialisme"
    ),

    // ── ABSURDISME ────────────────────────────────────────────────────────────
    Quote(
        "Seseorang harus membayangkan Sisifus bahagia.",
        "One must imagine Sisyphus happy.",
        "Albert Camus", "Absurdisme"
    ),
    Quote(
        "Di kedalaman musim dingin, aku akhirnya menyadari bahwa di dalam diriku terdapat musim panas yang tak terkalahkan.",
        "In the depth of winter, I finally learned that within me there lay an invincible summer.",
        "Albert Camus", "Absurdisme"
    ),
    Quote(
        "Satu-satunya cara menghadapi dunia yang tidak bebas adalah dengan menjadi begitu bebas sehingga keberadaanmu sendiri adalah pemberontakan.",
        "The only way to deal with an unfree world is to become so absolutely free that your very existence is an act of rebellion.",
        "Albert Camus", "Absurdisme"
    ),
    Quote(
        "Absurd lahir dari konfrontasi antara kebutuhan manusia dan keheningan dunia yang tak masuk akal.",
        "The absurd is born from the confrontation between the human need and the unreasonable silence of the world.",
        "Albert Camus", "Absurdisme"
    ),
    Quote(
        "Hidup adalah petualangan yang berani atau bukan apa-apa.",
        "Life is either a daring adventure or nothing at all.",
        "Helen Keller", "Absurdisme"
    ),

    // ── TIMUR ─────────────────────────────────────────────────────────────────
    Quote(
        "Perjalanan seribu mil dimulai dari satu langkah.",
        "The journey of a thousand miles begins with a single step.",
        "Lao Tzu", "Timur"
    ),
    Quote(
        "Mengenal orang lain adalah kecerdasan; mengenal dirimu sendiri adalah kebijaksanaan sejati.",
        "Knowing others is intelligence; knowing yourself is true wisdom.",
        "Lao Tzu", "Timur"
    ),
    Quote(
        "Pikiran adalah segalanya. Apa yang kamu pikirkan, itulah yang kamu menjadi.",
        "The mind is everything. What you think, you become.",
        "Buddha", "Timur"
    ),
    Quote(
        "Kedamaian datang dari dalam. Jangan mencarinya di luar.",
        "Peace comes from within. Do not seek it without.",
        "Buddha", "Timur"
    ),
    Quote(
        "Tidak masalah seberapa lambat kamu pergi, selama kamu tidak berhenti.",
        "It does not matter how slowly you go as long as you do not stop.",
        "Konfusius", "Timur"
    ),
    Quote(
        "Hidup sebenarnya sederhana, tapi kita bersikeras mempersulit diri.",
        "Life is really simple, but we insist on making it complicated.",
        "Konfusius", "Timur"
    ),
    Quote(
        "Di tengah kekacauan, ada juga kesempatan.",
        "In the midst of chaos, there is also opportunity.",
        "Sun Tzu", "Timur"
    ),
    Quote(
        "Ketika kamu menyadari tidak ada yang kurang, seluruh dunia menjadi milikmu.",
        "When you realize there is nothing lacking, the whole world belongs to you.",
        "Lao Tzu", "Timur"
    )
)

package com.example.data

object HadithCatalogData {

    val allThemes = listOf(
        "Semua Tema",
        "Iman & Aqidah",
        "Thaharah (Bersuci)",
        "Sholat",
        "Puasa",
        "Zakat & Sedekah",
        "Akhlak & Adab",
        "Muamalah & Harta",
        "Keluarga & Pernikahan",
        "Zikir & Doa",
        "Ilmu & Sunnah",
        "Edukasi Hadits Lemah/Palsu"
    )

    val bookmarkFolders = listOf(
        "Amalan Harian",
        "Bahan Ceramah & Kajian",
        "Hafalan Prioritas",
        "Doa & Zikir Pilihan",
        "Catatan Pribadi"
    )

    val nineImams = listOf(
        ImamBookInfo(
            id = "bukhari",
            apiSlug = "bukhari",
            name = "Shahih Bukhari",
            arabicName = "صحيح البخاري",
            authorFullName = "Abu Abdullah Muhammad bin Ismail Al-Bukhari",
            birthDeathHijri = "194 H – 256 H (Bukhara - Khartank)",
            totalHadithsInBook = 7008,
            description = "Kitab hadits paling shahih setelah Al-Qur'an (Al-Jami' Al-Musnad Ash-Shahih). Disusun selama 16 tahun oleh Imam Bukhari dengan seleksi sanad yang sangat ketat.",
            methodology = "Mensyaratkan perawi harus sezaman (Mu'asharah) dan terbukti pernah bertemu langsung (Liqa') dengan gurunya serta dikenal tsiqah (adil dan dhabith sempurna)."
        ),
        ImamBookInfo(
            id = "muslim",
            apiSlug = "muslim",
            name = "Shahih Muslim",
            arabicName = "صحيح مسلم",
            authorFullName = "Abu Al-Husain Muslim bin Al-Hajjaj An-Naisaburi",
            birthDeathHijri = "204 H – 261 H (Naisabur)",
            totalHadithsInBook = 5362,
            description = "Kitab tershahih kedua setelah Shahih Bukhari. Keunggulannya adalah sistematika penyusunan bab yang sangat rapi dan mengumpulkan seluruh jalur sanad dalam satu tempat.",
            methodology = "Mensyaratkan Mu'asharah (sezaman) dengan kemungkinan bertemu tanpa terindikasi tadlis, serta sangat teliti dalam perbedaan lafaz antar perawi."
        ),
        ImamBookInfo(
            id = "abu-daud",
            apiSlug = "abu-daud",
            name = "Sunan Abu Daud",
            arabicName = "سنن أبي داود",
            authorFullName = "Abu Daud Sulaiman bin Al-Asy'ats As-Sijistani",
            birthDeathHijri = "202 H – 275 H (Sijistan - Bashrah)",
            totalHadithsInBook = 4590,
            description = "Fokus utama kitab ini adalah hadits-hadits hukum fiqih (Ahkam). Menjadi rujukan utama para fuqaha dalam istinbath hukum syariat.",
            methodology = "Memuat hadits Shahih, Hasan, dan menjelaskan secara jujur bila ada hadits yang memiliki kelemahan berat (wahin syadid)."
        ),
        ImamBookInfo(
            id = "tirmidzi",
            apiSlug = "tirmidzi",
            name = "Jami' At-Tirmidzi",
            arabicName = "جامع الترمذي",
            authorFullName = "Abu Isa Muhammad bin Isa At-Tirmidzi",
            birthDeathHijri = "209 H – 279 H (Tirmidz)",
            totalHadithsInBook = 3891,
            description = "Dikenal sebagai Sunan At-Tirmidzi. Sangat istimewa karena mencantumkan derajat setiap hadits dan perbandingan mazhab fiqih para ulama salaf.",
            methodology = "Mempopulerkan istilah 'Hasan' dan 'Hasan Shahih', menyebutkan 'illat hadits, serta merangkum pendapat sahabat dan imam mazhab."
        ),
        ImamBookInfo(
            id = "nasai",
            apiSlug = "nasai",
            name = "Sunan An-Nasa'i",
            arabicName = "سنن النسائي",
            authorFullName = "Abu Abdurrahman Ahmad bin Syu'aib An-Nasa'i",
            birthDeathHijri = "215 H – 303 H (Nasa - Palestina)",
            totalHadithsInBook = 5662,
            description = "Al-Mujtaba (Sunan Ash-Shughra). Di antara kitab Sunan yang empat, Sunan An-Nasa'i memiliki syarat perawi paling ketat dan paling sedikit hadits lemahnya.",
            methodology = "Menggabungkan keahlian fiqih dan kritik sanad ('Ilal) tingkat tinggi dengan bab-bab istinbath hukum yang sangat detail."
        ),
        ImamBookInfo(
            id = "ibnu-majah",
            apiSlug = "ibnu-majah",
            name = "Sunan Ibnu Majah",
            arabicName = "سنن ابن ماجه",
            authorFullName = "Abu Abdullah Muhammad bin Yazid Ibnu Majah Al-Qazwini",
            birthDeathHijri = "209 H – 273 H (Qazwin)",
            totalHadithsInBook = 4332,
            description = "Pelengkap Kutubus Sittah (6 Kitab Induk). Memiliki muqaddimah tentang keutamaan mengikuti Sunnah dan ilmu yang sangat indah.",
            methodology = "Memuat banyak zawaid (hadits tambahan yang tidak ada di 5 kitab lainnya), mencakup derajat Shahih, Hasan, Dhaif, hingga sebagian riwayat munkar yang telah ditahqiq ulama."
        ),
        ImamBookInfo(
            id = "ahmad",
            apiSlug = "ahmad",
            name = "Musnad Ahmad",
            arabicName = "مسند الإمام أحمد",
            authorFullName = "Abu Abdullah Ahmad bin Muhammad bin Hanbal Asy-Syaibani",
            birthDeathHijri = "164 H – 241 H (Baghdad)",
            totalHadithsInBook = 26363,
            description = "Ensiklopedia hadits raksasa yang disusun berdasarkan nama Sahabat perawi (Musnad), mulai dari 10 sahabat yang dijamin masuk surga hingga sahabat lainnya.",
            methodology = "Mengumpulkan seluruh riwayat yang pernah dijadikan hujjah di kalangan ulama hadits dari sekitar 900 sahabat Nabi ﷺ."
        ),
        ImamBookInfo(
            id = "malik",
            apiSlug = "malik",
            name = "Muwatha' Malik",
            arabicName = "موطأ الإمام مالك",
            authorFullName = "Abu Abdullah Malik bin Anas Al-Ashbahi",
            birthDeathHijri = "93 H – 179 H (Madinah Al-Munawwarah)",
            totalHadithsInBook = 1587,
            description = "Kitab hadits dan fiqih tertua yang sampai kepada kita secara utuh, memadukan hadits Nabi ﷺ dengan atsar sahabat dan amalan penduduk Madinah.",
            methodology = "Menyeleksi riwayat langsung dari para Tabi'in di kota Madinah dengan sanad emas (Silsilah Adz-Dzahab: Malik dari Nafi' dari Ibnu Umar)."
        ),
        ImamBookInfo(
            id = "darimi",
            apiSlug = "darimi",
            name = "Sunan Ad-Darimi",
            arabicName = "سنن الدارمي",
            authorFullName = "Abu Muhammad Abdullah bin Abdurrahman Ad-Darimi",
            birthDeathHijri = "181 H – 255 H (Samarkand)",
            totalHadithsInBook = 3503,
            description = "Kitab kesembilan dalam Kutubut Tis'ah dan merupakan salah satu guru dari Imam Bukhari, Muslim, Abu Daud, dan Tirmidzi.",
            methodology = "Diawali dengan bab keutamaan Nabi ﷺ dan adab menuntut ilmu, kemudian disusun secara sistematis mengikuti bab-bab fiqih."
        )
    )

    val sahabatNarrators = listOf(
        SahabatNarratorInfo(
            id = "abu_hurairah",
            name = "Abu Hurairah radhiyallahu 'anhu",
            arabicName = "أبو هريرة عبد الرحمن بن صخر الدوسي",
            title = "Hafizhnya Para Sahabat (Perawi Terbanyak)",
            totalNarratedCount = 5374,
            wafatYear = "57 H (Madinah, usia 78 tahun)",
            bioSummary = "Nama aslinya Abdurrahman bin Shakhr Ad-Dausi. Beliau mendedikasikan hidupnya di Shuffah Masjid Nabawi untuk menyertai Rasulullah ﷺ kemana pun beliau pergi.",
            virtues = "Mendapatkan doa khusus dari Rasulullah ﷺ agar tidak pernah melupakan ilmu dan hadits yang didengarnya (HR. Bukhari No. 118)."
        ),
        SahabatNarratorInfo(
            id = "ibnu_umar",
            name = "Abdullah bin Umar radhiyallahu 'anhuma",
            arabicName = "عبد الله بن عمر بن الخطاب",
            title = "Sahabat Paling Ketat Meneladani Sunnah",
            totalNarratedCount = 2630,
            wafatYear = "73 H (Makkah, usia 84 tahun)",
            bioSummary = "Putra Khalifah Umar bin Khattab. Masuk Islam sejak kecil di Makkah dan ikut berhijrah ke Madinah. Terkenal sangat berhati-hati dalam meriwayatkan hadits tanpa mengurangi atau menambah satu huruf pun.",
            virtues = "Aisyah radhiyallahu 'anha berkata: 'Tidak ada seorang pun yang lebih mengikuti jejak langkah Nabi ﷺ di tempat-tempat singgahnya melebihi Ibnu Umar.'"
        ),
        SahabatNarratorInfo(
            id = "anas_bin_malik",
            name = "Anas bin Malik radhiyallahu 'anhu",
            arabicName = "أنس بن مالك الأنصاري",
            title = "Khadim (Pelayan Setia) Rasulullah ﷺ",
            totalNarratedCount = 2286,
            wafatYear = "93 H (Bashrah, usia >100 tahun)",
            bioSummary = "Sejak usia 10 tahun diserahkan oleh ibunya (Ummu Sulaim) untuk melayani kebutuhan Rasulullah ﷺ selama 10 tahun penuh di Madinah.",
            virtues = "Didoakan langsung oleh Nabi ﷺ: 'Ya Allah, perbanyaklah harta dan keturunannya, serta berkahilah apa yang Engkau berikan kepadanya' (HR. Bukhari)."
        ),
        SahabatNarratorInfo(
            id = "aisyah",
            name = "Aisyah Ummul Mukminin radhiyallahu 'anha",
            arabicName = "عائشة بنت أبي بكر الصديق",
            title = "Faqihah Ummul Mukminin & Ulama Wanita Terbesar",
            totalNarratedCount = 2210,
            wafatYear = "58 H (Madinah, dimakamkan di Baqi')",
            bioSummary = "Putri Abu Bakar Ash-Shiddiq dan istri tercinta Rasulullah ﷺ. Menjadi rujukan utama para sahabat senior dalam urusan hadits rumah tangga Nabi ﷺ, fiqih, waris, hingga pengobatan.",
            virtues = "Az-Zuhri berkata: 'Seandainya ilmu Aisyah dikumpulkan dan dibandingkan dengan ilmu seluruh wanita, niscaya ilmu Aisyah lebih utama.'"
        ),
        SahabatNarratorInfo(
            id = "ibnu_abbas",
            name = "Abdullah bin Abbas radhiyallahu 'anhuma",
            arabicName = "عبد الله بن عباس بن عبد المطلب",
            title = "Hibrul Ummah & Turjumanul Qur'an",
            totalNarratedCount = 1660,
            wafatYear = "68 H (Thaif, usia 71 tahun)",
            bioSummary = "Sepupu Rasulullah ﷺ yang sangat gigih menuntut ilmu dari para sahabat Anshar dan Muhajirin setelah wafatnya Nabi ﷺ.",
            virtues = "Nabi ﷺ merangkulnya dan berdoa: 'Allahumma faqqihhu fid-din wa 'allimhut-ta'wil' (Ya Allah, pahamkanlah ia dalam agama dan ajarkanlah tafsir kepadanya)."
        ),
        SahabatNarratorInfo(
            id = "jabir_bin_abdillah",
            name = "Jabir bin Abdillah radhiyallahu 'anhuma",
            arabicName = "جابر بن عبد الله الأنصاري",
            title = "Mufti Madinah & Perawi Manasik Haji Nabi ﷺ",
            totalNarratedCount = 1540,
            wafatYear = "78 H (Madinah, usia 94 tahun)",
            bioSummary = "Sahabat Anshar yang mengikuti 19 peperangan bersama Rasulullah ﷺ. Meriwayatkan hadits panjang tentang sifat Haji Wada' Nabi ﷺ secara sangat rinci.",
            virtues = "Memiliki halaqah ilmu besar di Masjid Nabawi yang dihadiri para tabi'in dari seluruh penjuru dunia Islam."
        ),
        SahabatNarratorInfo(
            id = "abu_said_alkhudri",
            name = "Abu Sa'id Al-Khudri radhiyallahu 'anhu",
            arabicName = "أبو سعيد الخدري سعد بن مالك الأنصاري",
            title = "Mufti & Ulama Muda Sahabat Anshar",
            totalNarratedCount = 1170,
            wafatYear = "74 H (Madinah)",
            bioSummary = "Nama aslinya Sa'ad bin Malik Al-Anshari. Banyak meriwayatkan hadits tentang adab, zikir, fiqih ibadah, dan nasihat kelembutan hati.",
            virtues = "Dikenal sebagai salah satu sahabat yang paling dermawan dengan ilmunya dan membimbing generasi tabi'in muda."
        ),
        SahabatNarratorInfo(
            id = "umar_bin_khattab",
            name = "Umar bin Khattab radhiyallahu 'anhu",
            arabicName = "عمر بن الخطاب الفاروق",
            title = "Al-Faruq & Amirul Mukminin",
            totalNarratedCount = 537,
            wafatYear = "23 H (Syahid di Madinah)",
            bioSummary = "Khalifah Rasyidin kedua yang membedakan antara yang haq dan bathil. Meski jumlah riwayatnya selektif karena ketegasannya menjaga kemurnian hadits, riwayatnya menjadi fondasi utama Islam (seperti hadits Niat dan hadits Jibril).",
            virtues = "Rasulullah ﷺ bersabda: 'Sesungguhnya Allah telah menjadikan kebenaran pada lisan dan hati Umar' (HR. Tirmidzi)."
        )
    )
}

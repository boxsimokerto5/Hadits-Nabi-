package com.example.data

object HadithSeedData {

    fun getInitialHadiths(): List<HadithEntity> = listOf(
        // 1. Niat (Bukhari 1)
        HadithEntity(
            id = "bukhari_1",
            bookId = "bukhari",
            bookName = "Shahih Bukhari",
            hadithNumber = 1,
            title = "Segala Amal Tergantung pada Niat",
            arabicText = "إِنَّمَا الأَعْمَالُ بِالنِّيَّاتِ، وَإِنَّمَا لِكُلِّ امْرِئٍ مَا نَوَى، فَمَنْ كَانَتْ هِجْرَتُهُ إِلَى دُنْيَا يُصِيبُهَا أَوْ إِلَى امْرَأَةٍ يَنْكِحُهَا فَهِجْرَتُهُ إِلَى مَا هَاجَرَ إِلَيْهِ",
            latinTransliteration = "Innamal a'maalu bin-niyyaat, wa innamaa likulli imri-in maa nawaa, faman kaanat hijratuhu ilaa dunyaa yushiibuhaa au ilaa imra-atin yankihuhaa fahijratuhu ilaa maa haajara ilaiih.",
            indonesianTranslation = "Sesungguhnya setiap amalan tergantung pada niatnya. Dan sesungguhnya setiap orang akan mendapatkan sesuai dengan apa yang ia niatkan. Barangsiapa yang hijrahnya karena dunia yang ingin diraihnya atau karena wanita yang ingin dinikahinya, maka hijrahnya adalah kepada apa yang dia niatkan tersebut.",
            grade = "SHAHIH",
            gradeScholar = "Muttafaqun 'Alaih (HR. Bukhari No. 1 & Muslim No. 1907)",
            themeCategory = "Iman & Aqidah",
            narratorId = "umar_bin_khattab",
            narratorName = "Umar bin Khattab radhiyallahu 'anhu",
            sunnahTimeSlot = "Pagi & Bangun Tidur",
            syarahFiqih = "• Hadits ini disebut oleh Imam Asy-Syafi'i dan Imam Ahmad sebagai sepertiga ilmu Islam karena seluruh amal ibadah hati, lisan, dan anggota badan berporos pada keikhlasan niat.\n• Fungsi niat ada dua: (1) Membedakan antara ibadah dengan kebiasaan sehari-hari (misal mandi wajib vs mandi segar), dan (2) Membedakan tingkatan ibadah satu dengan lainnya (sholat wajib vs sholat sunnah).\n• Amal yang secara lahiriah tampak shalih namun diniatkan untuk pujian atau dunia semata tidak bernilai pahala di sisi Allah."
        ),
        // 2. Rukun Islam & Ihsan (Muslim 8)
        HadithEntity(
            id = "muslim_8",
            bookId = "muslim",
            bookName = "Shahih Muslim",
            hadithNumber = 8,
            title = "Islam, Iman, dan Ihsan (Hadits Jibril)",
            arabicText = "الإِحْسَانُ أَنْ تَعْبُدَ اللَّهَ كَأَنَّكَ تَرَاهُ، فَإِنْ لَمْ تَكُنْ تَرَاهُ فَإِنَّهُ يَرَاكَ",
            latinTransliteration = "Al-Ihsaanu an ta'budallaaha ka-annaka taraahu, fa-in lam takun taraahu fa-innahu yaraak.",
            indonesianTranslation = "Ihsan adalah engkau beribadah kepada Allah seakan-akan engkau melihat-Nya, dan jika engkau tidak mampu melihat-Nya, maka sesungguhnya Dia senantiasa melihatmu.",
            grade = "SHAHIH",
            gradeScholar = "HR. Muslim No. 8 (Shahih)",
            themeCategory = "Iman & Aqidah",
            narratorId = "umar_bin_khattab",
            narratorName = "Umar bin Khattab radhiyallahu 'anhu",
            sunnahTimeSlot = "Masjid & Ibadah",
            syarahFiqih = "• Dikenal sebagai Ummus Sunnah (Induk Sunnah) karena merangkum seluruh tingkatan agama Islam: Syariat lahiriah (Islam), Keyakinan batin (Iman), dan Kesempurnaan penghayatan (Ihsan).\n• Menghadirkan Muraqabatullah (kesadaran diawasi Allah) dalam setiap keadaan akan mencegah seorang hamba dari maksiat saat sendirian."
        ),
        // 3. Menjaga Kemurnian Sunnah (Bukhari 2697)
        HadithEntity(
            id = "bukhari_2697",
            bookId = "bukhari",
            bookName = "Shahih Bukhari",
            hadithNumber = 2697,
            title = "Kewajiban Mengikuti Tuntunan Sunnah Nabi ﷺ",
            arabicText = "مَنْ أَحْدَثَ فِي أَمْرِنَا هَذَا مَا لَيْسَ فِيهِ فَهُوَ رَدٌّ",
            latinTransliteration = "Man ahdatsa fii amrinaa haadzaa maa laisa fiihi fahuwa raddun.",
            indonesianTranslation = "Barangsiapa membuat-buat perkara baru dalam urusan (agama) kami ini yang tidak ada asalnya, maka perkara tersebut tertolak.",
            grade = "SHAHIH",
            gradeScholar = "Muttafaqun 'Alaih (HR. Bukhari No. 2697 & Muslim No. 1718)",
            themeCategory = "Ilmu & Sunnah",
            narratorId = "aisyah",
            narratorName = "Aisyah Ummul Mukminin radhiyallahu 'anha",
            sunnahTimeSlot = null,
            syarahFiqih = "• Jika hadits 'Innamal a'malu bin-niyyat' adalah timbangan batin (keikhlasan), maka hadits Aisyah ini adalah timbangan lahiriah (Mutaba'ah / kesesuaian dengan Sunnah Nabi ﷺ).\n• Ibadah hanya diterima apabila memenuhi dua syarat: Ikhlas karena Allah dan sesuai dengan tuntunan Rasulullah ﷺ."
        ),
        // 4. Siwak & Bersuci (Bukhari 887)
        HadithEntity(
            id = "bukhari_887",
            bookId = "bukhari",
            bookName = "Shahih Bukhari",
            hadithNumber = 887,
            title = "Sunnah Bersiwak Setiap Hendak Sholat & Wudhu",
            arabicText = "لَوْلاَ أَنْ أَشُقَّ عَلَى أُمَّتِي لأَمَرْتُهُمْ بِالسِّوَاكِ عِنْدَ كُلِّ صَلاَةٍ",
            latinTransliteration = "Laulaa an asyuqqa 'alaa ummatii la-amartuhum bis-siwaaki 'inda kulli shalaah.",
            indonesianTranslation = "Seandainya tidak memberatkan umatku, niscaya aku perintahkan mereka untuk bersiwak (membersihkan gigi dan mulut) setiap kali hendak melaksanakan sholat.",
            grade = "SHAHIH",
            gradeScholar = "Muttafaqun 'Alaih (HR. Bukhari No. 887 & Muslim No. 252)",
            themeCategory = "Thaharah (Bersuci)",
            narratorId = "abu_hurairah",
            narratorName = "Abu Hurairah radhiyallahu 'anhu",
            sunnahTimeSlot = "Dini Hari & Bangun Tidur",
            syarahFiqih = "• Menunjukkan betapa besar perhatian Islam terhadap kebersihan mulut dan kesehatan gigi, khususnya ketika hendak bermunajat kepada Allah dalam sholat, saat berwudhu, membaca Al-Qur'an, dan ketika bangun tidur.\n• Bersiwak (atau menyikat gigi) adalah sunnah muakkadah yang mendatangkan keridhaan Allah."
        ),
        // 5. Keutamaan Wudhu Sempurna (Muslim 244)
        HadithEntity(
            id = "muslim_244",
            bookId = "muslim",
            bookName = "Shahih Muslim",
            hadithNumber = 244,
            title = "Wudhu Menggugurkan Dosa-dosa Kecil",
            arabicText = "مَنْ تَوَضَّأَ فَأَحْسَنَ الْوُضُوءَ خَرَجَتْ خَطَايَاهُ مِنْ جَسَدِهِ حَتَّى تَخْرُجَ مِنْ تَحْتِ أَظْفَارِهِ",
            latinTransliteration = "Man tawadhdha-a fa-ahsanal wudhuu-a kharajat khathaayaahu min jasadihii hattaa takhruja min tahti azhfaarih.",
            indonesianTranslation = "Barangsiapa berwudhu lalu menyempurnakan wudhunya dengan baik, maka dosa-dosanya akan keluar dari jasadnya hingga keluar dari bawah kuku-kukunya.",
            grade = "SHAHIH",
            gradeScholar = "HR. Muslim No. 244 (Shahih)",
            themeCategory = "Thaharah (Bersuci)",
            narratorId = "abu_hurairah",
            narratorName = "Abu Hurairah radhiyallahu 'anhu",
            sunnahTimeSlot = "Masjid & Ibadah",
            syarahFiqih = "• Menyempurnakan wudhu (Isbaghul Wudhu) mencakup menjalankan sunnah-sunnah wudhu seperti membaca basmalah, mencuci sela-sela jari, berkumur dan istinsyaq, serta meratakan air ke seluruh anggota wudhu.\n• Setiap tetesan air wudhu menjadi penggugur dosa kecil yang dilakukan oleh mata, tangan, lisan, dan kaki."
        ),
        // 6. Sholat Tepat Waktu & Berbakti (Bukhari 527)
        HadithEntity(
            id = "bukhari_527",
            bookId = "bukhari",
            bookName = "Shahih Bukhari",
            hadithNumber = 527,
            title = "Amalan yang Paling Dicintai Allah Ta'ala",
            arabicText = "سَأَلْتُ النَّبِيَّ صلى الله عليه وسلم أَىُّ الْعَمَلِ أَحَبُّ إِلَى اللَّهِ قَالَ: الصَّلاَةُ عَلَى وَقْتِهَا. قَالَ ثُمَّ أَىٌّ قَالَ: ثُمَّ بِرُّ الْوَالِدَيْنِ",
            latinTransliteration = "Sa-altun-nabiyya shallallaahu 'alaihi wasallam ayyul 'amali ahabbu ilallaah? Qaala: Ash-shalaatu 'alaa waqtihaa. Qaala tsumma ayyun? Qaala: Tsumma birrul waalidain.",
            indonesianTranslation = "Aku bertanya kepada Nabi ﷺ: 'Amalan apakah yang paling dicintai oleh Allah?' Beliau menjawab: 'Sholat tepat pada waktunya.' Aku bertanya lagi: 'Kemudian apa?' Beliau menjawab: 'Berbakti kepada kedua orang tua.'",
            grade = "SHAHIH",
            gradeScholar = "Muttafaqun 'Alaih (HR. Bukhari No. 527 & Muslim No. 85)",
            themeCategory = "Sholat",
            narratorId = "ibnu_umar",
            narratorName = "Abdullah bin Mas'ud / Ibnu Umar",
            sunnahTimeSlot = "Masjid & Ibadah",
            syarahFiqih = "• Menjaga sholat fardhu lima waktu di awal waktunya secara berjamaah adalah puncak ketaatan seorang hamba kepada Allah.\n• Setelah hak Allah melalui sholat, hak manusia terbesar yang wajib ditunaikan adalah Birrul Walidain (berbakti dan berbuat ihsan kepada ibu dan bapak)."
        ),
        // 7. Sholat Sunnah Rawatib 12 Rakaat (Muslim 728)
        HadithEntity(
            id = "muslim_728",
            bookId = "muslim",
            bookName = "Shahih Muslim",
            hadithNumber = 728,
            title = "Rumah di Surga Bagi yang Menjaga 12 Rakaat Rawatib",
            arabicText = "مَنْ صَلَّى اثْنَتَيْ عَشْرَةَ رَكْعَةً فِي يَوْمٍ وَلَيْلَةٍ بُنِيَ لَهُ بِهِنَّ بَيْتٌ فِي الْجَنَّةِ",
            latinTransliteration = "Man shallats-natai 'asyrata rak'atan fii yaumin wa lailatin buniya lahu bihinna baitun fil-jannah.",
            indonesianTranslation = "Barangsiapa mengerjakan sholat sunnah dua belas rakaat dalam sehari semalam, maka akan dibangunkan baginya sebuah rumah di surga.",
            grade = "SHAHIH",
            gradeScholar = "HR. Muslim No. 728 & Tirmidzi No. 415 (Shahih)",
            themeCategory = "Sholat",
            narratorId = "aisyah",
            narratorName = "Ummu Habibah & Aisyah radhiyallahu 'anhuma",
            sunnahTimeSlot = "Masjid & Ibadah",
            syarahFiqih = "• Rincian 12 rakaat Sunnah Rawatib Muakkadah (dalam riwayat Tirmidzi): 4 rakaat sebelum Zhuhur, 2 rakaat sesudah Zhuhur, 2 rakaat sesudah Maghrib, 2 rakaat sesudah Isya, dan 2 rakaat sebelum Subuh.\n• Sholat sunnah rawatib juga berfungsi menambal kekurangan kekhusyukan pada sholat wajib."
        ),
        // 8. Keutamaan Puasa Ramadhan & Sunnah (Bukhari 1904)
        HadithEntity(
            id = "bukhari_1904",
            bookId = "bukhari",
            bookName = "Shahih Bukhari",
            hadithNumber = 1904,
            title = "Puasa Adalah Perisai & Memiliki Pintu Ar-Rayyan",
            arabicText = "الصِّيَامُ جُنَّةٌ فَلاَ يَرْفُثْ وَلاَ يَجْهَلْ، وَإِنِ امْرُؤٌ قَاتَلَهُ أَوْ شَاتَمَهُ فَلْيَقُلْ إِنِّي صَائِمٌ مَرَّتَيْنِ",
            latinTransliteration = "Ash-shiyaamu junnatun falaa yarfuts walaa yajhal, wa inim-ru-un qaatalahu au syaatamahu falyaqul innii shaa-imun marratain.",
            indonesianTranslation = "Puasa adalah perisai. Maka janganlah berkata kotor dan jangan pula berbuat bodoh (emosi). Apabila ada seseorang yang mengajaknya bertengkar atau mencacinya, hendaklah ia mengatakan: 'Sesungguhnya aku sedang berpuasa' (dua kali).",
            grade = "SHAHIH",
            gradeScholar = "Muttafaqun 'Alaih (HR. Bukhari No. 1904 & Muslim No. 1151)",
            themeCategory = "Puasa",
            narratorId = "abu_hurairah",
            narratorName = "Abu Hurairah radhiyallahu 'anhu",
            sunnahTimeSlot = null,
            syarahFiqih = "• Hakikat puasa bukan sekadar menahan lapar dan dahaga, melainkan melatih pengendalian diri, lisan, dan amarah.\n• Orang yang berpuasa disunnahkan mengingatkan dirinya dan orang lain dengan lembut ('Inni shaa-im') agar terhindar dari pertikaian."
        ),
        // 9. Sedekah Tidak Mengurangi Harta (Muslim 2588)
        HadithEntity(
            id = "muslim_2588",
            bookId = "muslim",
            bookName = "Shahih Muslim",
            hadithNumber = 2588,
            title = "Sedekah Tidak Mengurangi Harta & Pemaafan Menambah Kemuliaan",
            arabicText = "مَا نَقَصَتْ صَدَقَةٌ مِنْ مَالٍ، وَمَا زَادَ اللَّهُ عَبْدًا بِعَفْوٍ إِلاَّ عِزًّا، وَمَا تَوَاضَعَ أَحَدٌ لِلَّهِ إِلاَّ رَفَعَهُ اللَّهُ",
            latinTransliteration = "Maa naqashat shadaqatun min maal, wa maa zaadallaahu 'abdan bi'afwin illaa 'izzaa, wa maa tawaadha'a ahadun lillaahi illaa rafa'ahullaah.",
            indonesianTranslation = "Sedekah tidaklah mengurangi harta. Tidaklah Allah menambahkan kepada seorang hamba sifat pemaaf melainkan kemuliaan, dan tidaklah seseorang merendahkan hati (tawadhu') karena Allah melainkan Allah akan mengangkat derajatnya.",
            grade = "SHAHIH",
            gradeScholar = "HR. Muslim No. 2588 (Shahih)",
            themeCategory = "Zakat & Sedekah",
            narratorId = "abu_hurairah",
            narratorName = "Abu Hurairah radhiyallahu 'anhu",
            sunnahTimeSlot = "Pagi & Beraktivitas",
            syarahFiqih = "• Harta yang disedekahkan justru diberkahi oleh Allah, dijauhkan dari bencana, dan diganti dengan yang lebih baik di dunia maupun akhirat.\n• Membalas kesalahan dengan maaf bukanlah kelemahan, melainkan sumber izzah (wibawa dan kemuliaan) di hati manusia dan di sisi Allah."
        ),
        // 10. Senyum dan Wajah Cerah (Tirmidzi 1956 - HASAN SHAHIH)
        HadithEntity(
            id = "tirmidzi_1956",
            bookId = "tirmidzi",
            bookName = "Jami' At-Tirmidzi",
            hadithNumber = 1956,
            title = "Senyummu di Hadapan Saudaramu Adalah Sedekah",
            arabicText = "تَبَسُّمُكَ فِي وَجْهِ أَخِيكَ لَكَ صَدَقَةٌ، وَأَمْرُكَ بِالْمَعْرُوفِ وَنَهْيُكَ عَنِ الْمُنْكَرِ صَدَقَةٌ، وَإِرْشَادُكَ الرَّجُلَ فِي أَرْضِ الضَّلاَلِ لَكَ صَدَقَةٌ",
            latinTransliteration = "Tabassumuka fii wajhi akhiika laka shadaqah, wa amruka bil-ma'ruufi wa nahyuka 'anil-munkari shadaqah, wa irsyaadukar-rajula fii ardhidh-dhalaali laka shadaqah.",
            indonesianTranslation = "Senyummu di hadapan saudaramu adalah sedekah bagimu. Perintahmu kepada kebaikan dan laranganmu dari kemungkaran adalah sedekah. Menunjukkan jalan bagi orang yang tersesat juga sedekah bagimu.",
            grade = "HASAN",
            gradeScholar = "HR. Tirmidzi No. 1956 (Derajat: Hasan Gharib / Dishahihkan Al-Albani)",
            themeCategory = "Akhlak & Adab",
            narratorId = "abu_said_alkhudri",
            narratorName = "Abu Dzar Al-Ghifari / Abu Sa'id",
            sunnahTimeSlot = "Sore, Keluarga & Pergaulan",
            syarahFiqih = "• Pintu kebaikan dan sedekah dalam Sunnah Nabi ﷺ sangat luas, tidak terbatas pada harta benda.\n• Menampakkan wajah yang ramah dan tersenyum tulus saat bertemu sesama muslim mendatangkan pahala sedekah sekaligus mempererat ukhuwah."
        ),
        // 11. Adab Makan dan Minum dengan Tangan Kanan (Bukhari 5376)
        HadithEntity(
            id = "bukhari_5376",
            bookId = "bukhari",
            bookName = "Shahih Bukhari",
            hadithNumber = 5376,
            title = "Sunnah Membaca Basmalah & Makan dengan Tangan Kanan",
            arabicText = "يَا غُلاَمُ سَمِّ اللَّهَ، وَكُلْ بِيَمِينِكَ، وَكُلْ مِمَّا يَلِيكَ",
            latinTransliteration = "Yaa ghulaamu sammillaah, wa kul biyamiinika, wa kul mimmaa yaliik.",
            indonesianTranslation = "Wahai anak muda, sebutlah nama Allah (bacalah Bismillah), makanlah dengan tangan kananmu, dan makanlah dari bagian yang terdekat denganmu.",
            grade = "SHAHIH",
            gradeScholar = "Muttafaqun 'Alaih (HR. Bukhari No. 5376 & Muslim No. 2022)",
            themeCategory = "Akhlak & Adab",
            narratorId = "umar_bin_khattab",
            narratorName = "Umar bin Abi Salamah radhiyallahu 'anhu",
            sunnahTimeSlot = "Makan, Minum & Berpakaian",
            syarahFiqih = "• Tiga adab emas ketika makan yang diajarkan langsung oleh Rasulullah ﷺ: (1) Membaca 'Bismillah' di awal makan (jika lupa di awal, membaca 'Bismillahi awwalahu wa akhirahu'), (2) Menggunakan tangan kanan karena setan makan dan minum dengan tangan kiri, dan (3) Mengambil hidangan yang terdekat sebagai bentuk kesopanan."
        ),
        // 12. Meninggalkan Hal yang Tidak Bermanfaat (Tirmidzi 2317 - HASAN)
        HadithEntity(
            id = "tirmidzi_2317",
            bookId = "tirmidzi",
            bookName = "Jami' At-Tirmidzi",
            hadithNumber = 2317,
            title = "Tanda Kebaikan Islam Seseorang: Meninggalkan yang Sia-sia",
            arabicText = "مِنْ حُسْنِ إِسْلاَمِ الْمَرْءِ تَرْكُهُ مَا لاَ يَعْنِيهِ",
            latinTransliteration = "Min husni islaamil mar-i tarkuhu maa laa ya'niih.",
            indonesianTranslation = "Di antara tanda kebaikan kualitas keislaman seseorang adalah ia meninggalkan perkara yang tidak bermanfaat baginya (urusan dunia maupun akhiratnya).",
            grade = "HASAN",
            gradeScholar = "HR. Tirmidzi No. 2317 & Ibnu Majah No. 3976 (Derajat: Hasan)",
            themeCategory = "Akhlak & Adab",
            narratorId = "abu_hurairah",
            narratorName = "Abu Hurairah radhiyallahu 'anhu",
            sunnahTimeSlot = "Pagi & Beraktivitas",
            syarahFiqih = "• Imam An-Nawawi memasukkan hadits Hasan ini dalam Arba'in Nawawi karena menjadi prinsip manajemen waktu dan lisan terbaik bagi seorang muslim.\n• Mencakup menjaga lisan dari pembicaraan tanpa faedah, rasa ingin tahu berlebihan pada privasi orang lain, serta fokus pada amal produktif."
        ),
        // 13. Kejujuran dalam Jual Beli (Bukhari 2079)
        HadithEntity(
            id = "bukhari_2079",
            bookId = "bukhari",
            bookName = "Shahih Bukhari",
            hadithNumber = 2079,
            title = "Keberkahan Jual Beli dengan Kejujuran & Transparansi",
            arabicText = "الْبَيِّعَانِ بِالْخِيَارِ مَا لَمْ يَتَفَرَّقَا، فَإِنْ صَدَقَا وَبَيَّنَا بُورِكَ لَهُمَا فِي بَيْعِهِمَا، وَإِنْ كَتَمَا وَكَذَبَا مُحِقَتْ بَرَكَةُ بَيْعِهِمَا",
            latinTransliteration = "Al-bayyi'aani bil-khiyaari maa lam yatafarraqaa, fa-in shadaqaa wa bayyanaa buurika lahumaa fii bai'ihimaa, wa in katamaa wa kadzabaa muhiqat barakatu bai'ihimaa.",
            indonesianTranslation = "Penjual dan pembeli memiliki hak pilih (khiyar) selama keduanya belum berpisah. Apabila keduanya jujur dan menjelaskan (kondisi barang/harga dengan terang), maka jual beli keduanya akan diberkahi. Namun apabila keduanya menyembunyikan cacat dan berdusta, maka dihapuslah keberkahan jual beli keduanya.",
            grade = "SHAHIH",
            gradeScholar = "Muttafaqun 'Alaih (HR. Bukhari No. 2079 & Muslim No. 1532)",
            themeCategory = "Muamalah & Harta",
            narratorId = "ibnu_abbas",
            narratorName = "Hakim bin Hizam / Ibnu Abbas",
            sunnahTimeSlot = "Pagi & Beraktivitas",
            syarahFiqih = "• Kunci keberkahan rezeki dalam bisnis dan muamalah Islam adalah kejujuran (ash-shidq) dan keterbukaan informasi produk (al-bayan).\n• Menyembunyikan cacat barang mungkin tampak menguntungkan secara angka sesaat, namun mencabut keberkahan harta tersebut."
        ),
        // 14. Sebaik-baik Kalian Bagi Keluarganya (Tirmidzi 3895 - SHAHIH/HASAN SHAHIH)
        HadithEntity(
            id = "tirmidzi_3895",
            bookId = "tirmidzi",
            bookName = "Jami' At-Tirmidzi",
            hadithNumber = 3895,
            title = "Sebaik-baik Mukmin Adalah yang Paling Baik terhadap Keluarganya",
            arabicText = "خَيْرُكُمْ خَيْرُكُمْ لأَهْلِهِ وَأَنَا خَيْرُكُمْ لأَهْلِي",
            latinTransliteration = "Khairukum khairukum li-ahlihii wa anaa khairukum li-ahlii.",
            indonesianTranslation = "Sebaik-baik kalian adalah yang paling baik akhlaknya terhadap keluarganya (istri dan anak-anaknya), dan aku adalah orang yang paling baik di antara kalian terhadap keluargaku.",
            grade = "SHAHIH",
            gradeScholar = "HR. Tirmidzi No. 3895 & Ibnu Majah No. 1977 (Hasan Shahih)",
            themeCategory = "Keluarga & Pernikahan",
            narratorId = "aisyah",
            narratorName = "Aisyah Ummul Mukminin radhiyallahu 'anha",
            sunnahTimeSlot = "Sore, Keluarga & Pergaulan",
            syarahFiqih = "• Barometer sejati akhlak seseorang bukan hanya saat tampil di depan umum, melainkan bagaimana kelembutan, kesabaran, dan kasih sayangnya di dalam rumah tangganya sendiri.\n• Rasulullah ﷺ di rumah biasa membantu pekerjaan istrinya, menjahit pakaiannya sendiri, dan bercanda dengan penuh kasih sayang."
        ),
        // 15. Dua Kalimat Zikir yang Ringan di Lisan Berat di Timbangan (Bukhari 6406)
        HadithEntity(
            id = "bukhari_6406",
            bookId = "bukhari",
            bookName = "Shahih Bukhari",
            hadithNumber = 6406,
            title = "Dua Kalimat Zikir yang Dicintai Ar-Rahman & Berat di Mizan",
            arabicText = "كَلِمَتَانِ خَفِيفَتَانِ عَلَى اللِّسَانِ، ثَقِيلَتَانِ فِي الْمِيزَانِ، حَبِيبَتَانِ إِلَى الرَّحْمَنِ: سُبْحَانَ اللَّهِ وَبِحَمْدِهِ، سُبْحَانَ اللَّهِ الْعَظِيمِ",
            latinTransliteration = "Kalimataani khafiifataani 'alal-lisaani, tsaqiilataani fil-miizaani, habiibataani ilar-Rahmaan: Subhaanallaahi wa bihamdihi, Subhaanallaahil 'Azhiim.",
            indonesianTranslation = "Dua kalimat yang ringan diucapkan di lisan, namun sangat berat dalam timbangan amal (Mizan), dan sangat dicintai oleh Ar-Rahman (Allah Yang Maha Pengasih): 'Subhanallahi wa bihamdihi, Subhanallahil 'Azhim' (Maha Suci Allah dan segala puji bagi-Nya, Maha Suci Allah Yang Maha Agung).",
            grade = "SHAHIH",
            gradeScholar = "Muttafaqun 'Alaih (HR. Bukhari No. 6406 & Muslim No. 2694)",
            themeCategory = "Zikir & Doa",
            narratorId = "abu_hurairah",
            narratorName = "Abu Hurairah radhiyallahu 'anhu",
            sunnahTimeSlot = "Pagi & Beraktivitas",
            syarahFiqih = "• Imam Bukhari menutup kitab Shahih Bukhari dengan hadits agung ini sebagai pengingat bahwa amalan zikir yang mudah dilakukan kapan saja memiliki bobot pahala luar biasa di akhirat."
        ),
        // 16. Sunnah Sebelum Tidur & Mengibaskan Kasur (Bukhari 6320)
        HadithEntity(
            id = "bukhari_6320",
            bookId = "bukhari",
            bookName = "Shahih Bukhari",
            hadithNumber = 6320,
            title = "Adab Sebelum Tidur: Berwudhu, Mengibaskan Tempat Tidur & Berdoa",
            arabicText = "إِذَا أَوَى أَحَدُكُمْ إِلَى فِرَاشِهِ فَلْيَنْفُضْ فِرَاشَهُ بِدَاخِلَةِ إِزَارِهِ فَإِنَّهُ لاَ يَدْرِي مَا خَلَفَهُ عَلَيْهِ ثُمَّ يَقُولُ بِاسْمِكَ رَبِّي وَضَعْتُ جَنْبِي وَبِكَ أَرْفَعُهُ",
            latinTransliteration = "Idzaa awaa ahadukum ilaa firaasyihii falyanfudh firaasyahu bidaakhilati izaarihii fa-innahu laa yadrii maa khalafahu 'alaihi tsumma yaquulu: Bismika Rabbii wadha'tu janbii wa bika arfa'uh.",
            indonesianTranslation = "Apabila salah seorang di antara kalian hendak berbaring di tempat tidurnya, hendaklah ia mengibaskan tempat tidurnya dengan ujung kainnya, karena ia tidak tahu apa yang ada di atasnya sepeninggalnya. Kemudian hendaklah ia berdoa: 'Dengan nama-Mu wahai Rabbku aku meletakkan lambungku, dan dengan nama-Mu pula aku mengangkatnya.'",
            grade = "SHAHIH",
            gradeScholar = "Muttafaqun 'Alaih (HR. Bukhari No. 6320 & Muslim No. 2714)",
            themeCategory = "Zikir & Doa",
            narratorId = "abu_hurairah",
            narratorName = "Abu Hurairah radhiyallahu 'anhu",
            sunnahTimeSlot = "Malam & Sebelum Tidur",
            syarahFiqih = "• Mencakup sunnah praktis malam hari: berwudhu sebelum tidur, mengibaskan tempat tidur 3 kali sambil membaca basmalah, berbaring miring ke sisi kanan, membaca Ayat Kursi, 3 Qul (Al-Ikhlas, Al-Falaq, An-Nas) ditiupkan ke telapak tangan lalu diusapkan ke tubuh, serta membaca doa tidur."
        ),
        // 17. Menuntut Ilmu Jalan ke Surga (Abu Daud 3641)
        HadithEntity(
            id = "abudaud_3641",
            bookId = "abu-daud",
            bookName = "Sunan Abu Daud",
            hadithNumber = 3641,
            title = "Kemudahan Jalan Menuju Surga Bagi Penuntut Ilmu",
            arabicText = "مَنْ سَلَكَ طَرِيقًا يَطْلُبُ فِيهِ عِلْمًا سَلَكَ اللَّهُ بِهِ طَرِيقًا مِنْ طُرُقِ الْجَنَّةِ وَإِنَّ الْمَلاَئِكَةَ لَتَضَعُ أَجْنِحَتَهَا رِضًا لِطَالِبِ الْعِلْمِ",
            latinTransliteration = "Man salaka thariiqan yathlubu fiihi 'ilman salakallaahu bihii thariiqan min thuruqil jannah, wa innal malaa-ikata latadha'u ajnihatahaa ridhan lithaalibil 'ilm.",
            indonesianTranslation = "Barangsiapa menempuh suatu jalan untuk menuntut ilmu agama, maka Allah akan mudahkan baginya jalan menuju surga. Dan sesungguhnya para malaikat benar-benar meletakkan sayap-sayapnya karena ridha kepada penuntut ilmu.",
            grade = "SHAHIH",
            gradeScholar = "HR. Abu Daud No. 3641 & Muslim No. 2699 (Shahih)",
            themeCategory = "Ilmu & Sunnah",
            narratorId = "abu_hurairah",
            narratorName = "Abu Darda & Abu Hurairah radhiyallahu 'anhuma",
            sunnahTimeSlot = "Pagi & Beraktivitas",
            syarahFiqih = "• Menempuh jalan ilmu mencakup hadir secara fisik di majelis ilmu maupun mempelajari kitab-kitab hadits dan syariat melalui media pembelajaran dengan niat mengamalkannya."
        ),
        // 18. Contoh Hadits HASAN: Jangan Membahayakan Diri & Orang Lain (Ibnu Majah 2340)
        HadithEntity(
            id = "ibnumajah_2340",
            bookId = "ibnu-majah",
            bookName = "Sunan Ibnu Majah",
            hadithNumber = 2340,
            title = "Kaidah Agung: Tidak Boleh Membahayakan Diri & Orang Lain",
            arabicText = "لاَ ضَرَرَ وَلاَ ضِرَارَ",
            latinTransliteration = "Laa dharara wa laa dhiraar.",
            indonesianTranslation = "Tidak boleh melakukan perbuatan yang membahayakan (merugikan) diri sendiri dan tidak boleh pula membahayakan (membalas bahaya kepada) orang lain.",
            grade = "HASAN",
            gradeScholar = "HR. Ibnu Majah No. 2340 & Malik dalam Al-Muwatha' (Derajat: Hasan / Memiliki banyak jalur penguat)",
            themeCategory = "Muamalah & Harta",
            narratorId = "abu_said_alkhudri",
            narratorName = "Abu Sa'id Al-Khudri & Ibnu Abbas",
            sunnahTimeSlot = null,
            syarahFiqih = "• Salah satu dari 5 kaidah fiqih terbesar dalam Islam (Al-Qawa'id Al-Fiqhiyyah Al-Kubra): Adh-dhararu yuzaal (segala bentuk kemudharatan harus dihilangkan).\n• Menjadi dasar larangan merusak lingkungan, larangan mengonsumsi zat berbahaya bagi tubuh, serta larangan merugikan tetangga."
        ),
        // 19. Contoh Hadits DHAIF (Edukasi Kritik Sanad): Mengusap Wajah Setelah Doa di Luar Sholat (Tirmidzi 3386)
        HadithEntity(
            id = "tirmidzi_3386_dhaif",
            bookId = "tirmidzi",
            bookName = "Jami' At-Tirmidzi",
            hadithNumber = 3386,
            title = "[Edukasi Sanad Dhaif] Riwayat Mengusap Wajah Setelah Berdoa",
            arabicText = "كَانَ رَسُولُ اللَّهِ صلى الله عليه وسلم إِذَا رَفَعَ يَدَيْهِ فِي الدُّعَاءِ لَمْ يَحُطَّهُمَا حَتَّى يَمْسَحَ بِهِمَا وَجْهَهُ",
            latinTransliteration = "Kaana Rasuulullaahi shallallaahu 'alaihi wasallam idzaa rafa'a yadaihi fid-du'aa-i lam yahuththahumaa hattaa yamsaha bihimaa wajhah.",
            indonesianTranslation = "Dahulu Rasulullah ﷺ apabila mengangkat kedua tangannya dalam berdoa, beliau tidak menurunkannya hingga mengusapkan kedua tangannya ke wajahnya.",
            grade = "DHAIF",
            gradeScholar = "HR. Tirmidzi No. 3386 & Abu Daud No. 1485 — Dinilai Dhaif oleh Imam Ahmad, Al-Baihaqi, An-Nawawi (dalam Al-Majmu'), dan Al-Albani karena kelemahan perawi Hammad bin Isa Al-Juhani.",
            themeCategory = "Edukasi Hadits Lemah/Palsu",
            narratorId = "umar_bin_khattab",
            narratorName = "Riwayat dinisbatkan kepada Umar bin Khattab",
            sunnahTimeSlot = null,
            syarahFiqih = "⚠️ CATATAN ILMIAH DERAJAT HADITS (DHAIF):\n• Dalam sanadnya terdapat Hammad bin Isa Al-Juhani yang dinilai dhaif (lemah hafalannya) oleh para imam jarh wa ta'dil.\n• Sebagian ulama seperti Al-Hafizh Ibnu Hajar menyebutkan adanya jalur-jalur pendukung (syawahid), sementara Imam Ahmad, Imam Al-Baihaqi, dan Imam An-Nawawi menegaskan bahwa hadits-hadits bab berdoa yang shahih tidak menyebutkan mengusap wajah kecuali saat membaca perlindungan sebelum tidur.\n• Ditampilkan agar penuntut ilmu memahami perbedaan derajat hadits dalam khazanah fiqih."
        ),
        // 20. Contoh Hadits DHAIF (Ibnu Majah 422): Menyela-nyela Jenggot & Riwayat Wudhu Tertentu
        HadithEntity(
            id = "abudaud_5101_dhaif",
            bookId = "abu-daud",
            bookName = "Sunan Abu Daud",
            hadithNumber = 5101,
            title = "[Edukasi Sanad Dhaif] Riwayat Doa Ketika Telinga Berdenging",
            arabicText = "إِذَا طَنَّتْ أُذُنُ أَحَدِكُمْ فَلْيَذْكُرْنِي وَلْيُصَلِّ عَلَيَّ",
            latinTransliteration = "Idzaa thannat udzunu ahadikum falyadzkurnii walyushalli 'alayya.",
            indonesianTranslation = "Apabila telinga salah seorang dari kalian berdenging, maka hendaklah ia mengingatku dan bershalawat kepadaku...",
            grade = "DHAIF",
            gradeScholar = "HR. Thabrani & Ibnu Sunni — Dinilai Dhaif Jiddan (Sangat Lemah / Munkar) oleh Imam Al-Bukhari, Al-'Uqaili, dan Ibnu Qayyim dalam Al-Manar Al-Munif.",
            themeCategory = "Edukasi Hadits Lemah/Palsu",
            narratorId = "abu_hurairah",
            narratorName = "Riwayat dinisbatkan kepada Abu Rafi'",
            sunnahTimeSlot = null,
            syarahFiqih = "⚠️ CATATAN ILMIAH DERAJAT HADITS (DHAIF JIDDAN):\n• Imam Al-Bukhari berkata tentang perawi Muhammad bin Ubaidillah bin Abi Rafi': 'Munkarul hadits (haditsnya mungkar/sangat lemah).'\n• Bershalawat kepada Nabi ﷺ adalah ibadah agung yang sangat dianjurkan kapan saja berdasarkan dalil-dalil Shahih, namun mengkhususkannya karena keyakinan telinga berdenging tidak memiliki sandaran hadits yang shahih."
        ),
        // 21. Contoh Edukasi Hadits MAUDHU' (Palsu) yang Populer di Masyarakat: Tuntutlah Ilmu Sampai ke Negeri Cina
        HadithEntity(
            id = "edukasi_maudhu_1",
            bookId = "ibnu-majah",
            bookName = "Kajian Tahqiq Hadits Populer",
            hadithNumber = 9001,
            title = "[PERINGATAN MAUDHU'] 'Tuntutlah Ilmu Walaupun Sampai ke Negeri Cina'",
            arabicText = "اطْلُبُوا الْعِلْمَ وَلَوْ بِالصِّينِ",
            latinTransliteration = "Uthlubul 'ilma walau bish-Shiin.",
            indonesianTranslation = "(Ungkapan populer yang sering disangka hadits): 'Tuntutlah ilmu walaupun sampai ke negeri Cina.'",
            grade = "MAUDHU",
            gradeScholar = "Status: BATHIL / MAUDHU' (Palsu) — Ditegaskan oleh Imam Ibnu Hibban, Al-Hafizh Ibnul Jauzi (dalam Al-Maudhu'at), Imam Adz-Dzahabi, dan Syaikh Al-Albani.",
            themeCategory = "Edukasi Hadits Lemah/Palsu",
            narratorId = "anas_bin_malik",
            narratorName = "Dinisbatkan secara keliru kepada Anas bin Malik",
            sunnahTimeSlot = null,
            syarahFiqih = "🛑 PERINGATAN EDUKASI HADITS MAUDHU' (PALSU):\n• Mengapa penting mengetahui hadits Maudhu'? Agar kita tidak menyandarkan perkataan kepada Rasulullah ﷺ padahal beliau tidak pernah mengucapkannya.\n• Dalam jalur riwayat ini terdapat Abu 'Atikah Tharif bin Sulaiman yang disepakati para ulama hadits sebagai pemalsu hadits / matruk.\n• Cukuplah kita menggunakan hadits SHAHIH tentang keutamaan menuntut ilmu, seperti sabda Nabi ﷺ: 'Thalabul 'ilmi fariidhatun 'alaa kulli muslim' (Menuntut ilmu adalah kewajiban atas setiap muslim - HR. Ibnu Majah No. 224, Shahih) atau hadits 'Man salaka thariiqan yathlubu fiihi 'ilman...' (HR. Muslim)."
        ),
        // 22. Contoh Edukasi Hadits MAUDHU' (Palsu): Perselisihan Umatku Adalah Rahmat
        HadithEntity(
            id = "edukasi_maudhu_2",
            bookId = "ahmad",
            bookName = "Kajian Tahqiq Hadits Populer",
            hadithNumber = 9002,
            title = "[PERINGATAN MAUDHU'] 'Perselisihan Umatku Adalah Rahmat'",
            arabicText = "اخْتِلافُ أُمَّتِي رَحْمَةٌ",
            latinTransliteration = "Ikhtilaafu ummatii rahmah.",
            indonesianTranslation = "(Ungkapan populer yang bukan hadits Nabi ﷺ): 'Perselisihan (perpecahan) umatku adalah suatu rahmat.'",
            grade = "MAUDHU",
            gradeScholar = "Status: LA ASHLA LAHU (Tidak Ada Asal Sanadnya / Maudhu') — Dijelaskan oleh Imam As-Subki, Imam Ibnu Hazm, dan Syaikh Al-Albani dalam Silsilah Al-Ahadits Adh-Dha'ifah No. 57.",
            themeCategory = "Edukasi Hadits Lemah/Palsu",
            narratorId = "ibnu_abbas",
            narratorName = "Tidak Memiliki Sanad (La Ashla Lahu)",
            sunnahTimeSlot = null,
            syarahFiqih = "🛑 PERINGATAN EDUKASI HADITS MAUDHU' (TIDAK ADA SANADNYA):\n• Imam As-Subki berkata: 'Kalimat ini tidak dikenal di kalangan ahli hadits dan aku tidak menemukan sanadnya baik yang shahih, dhaif, maupun maudhu'.'\n• Al-Qur'an dan Sunnah yang Shahih justru memerintahkan persatuan (Al-Jama'ah) dan melarang perpecahan (QS. Hud: 118-119, QS. Ali Imran: 103). Adapun perbedaan ijtihad fiqih para ulama yang berlandaskan dalil disikapi dengan adab dan kelapangan dada, bukan dengan menganggap perpecahan sebagai rahmat."
        ),
        // 23. Sunnah Masuk & Keluar Masjid (Muslim 713)
        HadithEntity(
            id = "muslim_713",
            bookId = "muslim",
            bookName = "Shahih Muslim",
            hadithNumber = 713,
            title = "Doa Masuk dan Keluar Masjid Beserta Adab Melangkah",
            arabicText = "إِذَا دَخَلَ أَحَدُكُمُ الْمَسْجِدَ فَلْيَقُلِ اللَّهُمَّ افْتَحْ لِي أَبْوَابَ رَحْمَتِكَ وَإِذَا خَرَجَ فَلْيَقُلِ اللَّهُمَّ إِنِّي أَسْأَلُكَ مِنْ فَضْلِكَ",
            latinTransliteration = "Idzaa dakhala ahadukumul masjida falyaqul: Allaahummaftah lii abwaaba rahmatik. Wa idzaa kharaja falyaqul: Allaahumma innii as-aluka min fadhlik.",
            indonesianTranslation = "Apabila salah seorang di antara kalian masuk ke dalam masjid, hendaklah ia berdoa: 'Ya Allah, bukakanlah untukku pintu-pintu rahmat-Mu.' Dan apabila keluar dari masjid, hendaklah ia berdoa: 'Ya Allah, sesungguhnya aku memohon kepada-Mu karunia-Mu.'",
            grade = "SHAHIH",
            gradeScholar = "HR. Muslim No. 713 & Abu Daud No. 465 (Shahih)",
            themeCategory = "Zikir & Doa",
            narratorId = "abu_said_alkhudri",
            narratorName = "Abu Humaid / Abu Usaid Al-Anshari",
            sunnahTimeSlot = "Masjid & Ibadah",
            syarahFiqih = "• Sunnah ketika masuk masjid: mendahulukan kaki kanan, bershalawat kepada Nabi ﷺ, dan memohon dibukakan pintu rahmat karena di dalam masjid orang sibuk beribadah mendekatkan diri kepada Allah.\n• Ketika keluar masjid: mendahulukan kaki kiri dan memohon karunia (rezeki halal) karena akan kembali bertebaran mencari nafkah di muka bumi."
        ),
        // 24. Sunnah Menjenguk Orang Sakit (Bukhari 5649)
        HadithEntity(
            id = "bukhari_5649",
            bookId = "bukhari",
            bookName = "Shahih Bukhari",
            hadithNumber = 5649,
            title = "Sunnah Menjenguk & Mendoakan Kesembuhan Orang Sakit",
            arabicText = "لاَ بَأْسَ طَهُورٌ إِنْ شَاءَ اللَّهُ",
            latinTransliteration = "Laa ba'sa thahuurun in syaa-allaah.",
            indonesianTranslation = "Tidak mengapa, semoga sakitmu ini menjadi pembersih (penggugur dosa-dosa) insya Allah.",
            grade = "SHAHIH",
            gradeScholar = "HR. Bukhari No. 5656 (Shahih)",
            themeCategory = "Akhlak & Adab",
            narratorId = "ibnu_abbas",
            narratorName = "Abdullah bin Abbas radhiyallahu 'anhuma",
            sunnahTimeSlot = "Sore, Keluarga & Pergaulan",
            syarahFiqih = "• Menjenguk orang sakit ('Iyadatul Maridh) adalah salah satu dari 6 hak sesama muslim.\n• Rasulullah ﷺ mengajarkan untuk menghibur orang yang sakit dengan kalimat optimis dan mendoakan: 'Allaahumma Rabban-naasi adzhibil ba'sa isyfi Antasy-Syaafii laa syifaa-a illaa syifaa-uka syifaa-an laa yughaadiru saqamaa' (HR. Bukhari & Muslim)."
        )
    )

    val sunnah24HourGuides = listOf(
        SunnahDailyGuide(
            id = "sunnah_1_bangun",
            timePeriod = "Dini Hari & Bangun Tidur",
            timeRangeLabel = "03:30 – 05:00",
            title = "Bangun Tidur, Mengusap Wajah, Berdoa & Bersiwak",
            arabicPrayerOrMatan = "الْحَمْدُ لِلَّهِ الَّذِي أَحْيَانَا بَعْدَ مَا أَمَاتَنَا وَإِلَيْهِ النُّشُورُ",
            latinReading = "Alhamdulillaahil-ladzii ahyaanaa ba'da maa amaatanaa wa ilaihin-nusyuur.",
            translation = "Segala puji bagi Allah yang telah menghidupkan kami kembali setelah mematikan (menidurkan) kami, dan hanya kepada-Nya kami dibangkitkan.",
            practiceSteps = listOf(
                "Duduk sejenak dan mengusap bekas kantuk di wajah dengan kedua tangan.",
                "Membaca doa bangun tidur dengan penuh syukur.",
                "Membersihkan mulut dan gigi dengan siwak / sikat gigi.",
                "Mencuci kedua telapak tangan 3 kali sebelum memasukkannya ke bejana air wudhu."
            ),
            dalilReference = "HR. Bukhari No. 6312 & Muslim No. 2711",
            relatedHadithId = "bukhari_887"
        ),
        SunnahDailyGuide(
            id = "sunnah_2_subuh",
            timePeriod = "Pagi & Beraktivitas",
            timeRangeLabel = "05:00 – 08:30",
            title = "Sholat Sunnah Fajar 2 Rakaat, Zikir Pagi & Sholat Dhuha",
            arabicPrayerOrMatan = "رَكْعَتَا الْفَجْرِ خَيْرٌ مِنَ الدُّنْيَا وَمَا فِيهَا",
            latinReading = "Rak'atal fajri khairun minad-dunyaa wa maa fiihaa.",
            translation = "Dua rakaat sholat sunnah Fajar (Qabliyah Subuh) lebih baik daripada dunia dan seisinya.",
            practiceSteps = listOf(
                "Melaksanakan 2 rakaat ringan Qabliyah Subuh (membaca QS. Al-Kafirun di rakaat pertama & QS. Al-Ikhlas di rakaat kedua).",
                "Sholat Subuh tepat waktu (berjamaah di masjid bagi laki-laki).",
                "Duduk berzikir pagi hingga matahari terbit, serta menjaga sholat Dhuha minimal 2 rakaat."
            ),
            dalilReference = "HR. Muslim No. 725 (Dari Aisyah radhiyallahu 'anha)",
            relatedHadithId = "muslim_728"
        ),
        SunnahDailyGuide(
            id = "sunnah_3_makan",
            timePeriod = "Makan, Minum & Berpakaian",
            timeRangeLabel = "Sepanjang Hari",
            title = "Adab Makan, Minum 3 Tegukan & Memulai dengan Kanan",
            arabicPrayerOrMatan = "بِسْمِ اللَّهِ ... الْحَمْدُ لِلَّهِ الَّذِي أَطْعَمَنِي هَذَا وَرَزَقَنِيهِ مِنْ غَيْرِ حَوْلٍ مِنِّي وَلاَ قُوَّةٍ",
            latinReading = "Bismillaah... Alhamdulillaahil-ladzii ath'amanii haadzaa wa razaqaniihi min ghairi haulin minnii wa laa quwwah.",
            translation = "Dengan nama Allah... Segala puji bagi Allah yang telah memberiku makanan ini dan menganugerahkannya kepadaku tanpa daya dan kekuatan dariku.",
            practiceSteps = listOf(
                "Membaca 'Bismillah' sebelum makan/minum dan menggunakan tangan kanan.",
                "Minum dalam keadaan duduk dengan 3 kali tegukan (bernapas di luar gelas).",
                "Tidak mencela makanan; jika suka dimakan, jika tidak suka ditinggalkan.",
                "Mendahulukan anggota tubuh sebelah kanan (At-Tayammun) saat mengenakan pakaian dan alas kaki."
            ),
            dalilReference = "HR. Bukhari No. 5376, Muslim No. 2028 & Abu Daud No. 4023",
            relatedHadithId = "bukhari_5376"
        ),
        SunnahDailyGuide(
            id = "sunnah_4_masjid",
            timePeriod = "Masjid & Ibadah",
            timeRangeLabel = "5 Waktu Sholat",
            title = "Berwudhu dari Rumah, Melangkah ke Masjid & Tahiyatul Masjid",
            arabicPrayerOrMatan = "اللَّهُمَّ افْتَحْ لِي أَبْوَابَ رَحْمَتِكَ",
            latinReading = "Allaahummaftah lii abwaaba rahmatik.",
            translation = "Ya Allah, bukakanlah untukku pintu-pintu rahmat-Mu.",
            practiceSteps = listOf(
                "Menyempurnakan wudhu dari rumah sebelum berangkat ke masjid.",
                "Melangkah masuk masjid dengan kaki kanan sambil membaca doa masuk masjid.",
                "Mengerjakan sholat sunnah Tahiyatul Masjid 2 rakaat sebelum duduk.",
                "Menjaga 12 rakaat sholat Sunnah Rawatib sepanjang hari."
            ),
            dalilReference = "HR. Muslim No. 666 & No. 713",
            relatedHadithId = "muslim_713"
        ),
        SunnahDailyGuide(
            id = "sunnah_5_sosial",
            timePeriod = "Sore, Keluarga & Pergaulan",
            timeRangeLabel = "15:30 – 20:00",
            title = "Menebar Salam, Senyum, Lembut pada Keluarga & Zikir Petang",
            arabicPrayerOrMatan = "تَبَسُّمُكَ فِي وَجْهِ أَخِيكَ لَكَ صَدَقَةٌ",
            latinReading = "Tabassumuka fii wajhi akhiika laka shadaqah.",
            translation = "Senyummu di hadapan saudaramu adalah sedekah bagimu.",
            practiceSteps = listOf(
                "Menebarkan salam ('Assalamu'alaikum') dan wajah ramah kepada keluarga dan sesama muslim.",
                "Membaca Zikir Petang setelah waktu Ashar hingga menjelang Maghrib.",
                "Membantu urusan keluarga di rumah dan berbicara dengan tutur kata yang baik."
            ),
            dalilReference = "HR. Tirmidzi No. 1956 & No. 3895",
            relatedHadithId = "tirmidzi_1956"
        ),
        SunnahDailyGuide(
            id = "sunnah_6_tidur",
            timePeriod = "Malam & Sebelum Tidur",
            timeRangeLabel = "21:00 – Tidur",
            title = "Wudhu Sebelum Tidur, Mengibaskan Kasur, 3 Qul & Doa Tidur",
            arabicPrayerOrMatan = "بِاسْمِكَ رَبِّي وَضَعْتُ جَنْبِي وَبِكَ أَرْفَعُهُ",
            latinReading = "Bismika Rabbii wadha'tu janbii wa bika arfa'uh.",
            translation = "Dengan nama-Mu wahai Rabbku aku meletakkan lambungku, dan dengan nama-Mu pula aku mengangkatnya.",
            practiceSteps = listOf(
                "Berwudhu sebagaimana wudhu untuk sholat sebelum naik ke tempat tidur.",
                "Mengibaskan tempat tidur 3 kali sambil membaca Bismillah.",
                "Menyatukan kedua telapak tangan, membaca Al-Ikhlas, Al-Falaq, dan An-Nas, meniupkannya lalu mengusapkan ke seluruh tubuh yang terjangkau (3 kali).",
                "Membaca Ayat Kursi (QS. Al-Baqarah: 255) dan tidur miring ke lambung kanan."
            ),
            dalilReference = "HR. Bukhari No. 5017, No. 6320 & Muslim No. 2714",
            relatedHadithId = "bukhari_6320"
        )
    )
}

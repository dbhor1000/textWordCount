import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


        //Напишите приложение, которое на вход через консоль, получит текст и выдаст статистику:
        //
        //1. Количество слов в тексте
        //2. TOP10 самых часто упоминаемых слов, упорядоченных по количеству упоминаний в
        //обратном порядке. В случае одинакового количества упоминаний слова должны быть отсортированы по алфавиту.

        //---->
        //Для выполнения задачи необходимо:
        //1. Посчитать количество слов в тексте. +++
        //2. Определить количество повторов слов.
        //3. Вывести 10 слов, которые упоминаются наиболее часто.
        //4. Отсортировать слова по алфавиту.


        //Исходный текст

        String someText = "Юкиэ Тири родилась в семье айнов в 1903 году (36 Мэйдзи) в семье айнов — отца Такаёси и матери Нами, принадлежащих к общине Тикафуми региона Исикари (современный Ноборибэцу) на острове Хоккайдо, где теперь находится мемориальный зал, посвящённый Юкиэ. В исполнении бабушки, с которой она жила на Хоккайдо, с самого детства слушала песни, истории и сказания айнов. В возрасте шести лет родители отправили её к тётке, которая жила со своей пожилой матерью по имени Монасинуку, в совершенстве владевшей айнским языком и почти не говорившей по-японски. Юкиэ познакомилась с айнским фольклором, который в это время уже начал вымирать. Несмотря на непростые отношения со сверстниками, девочка с успехом училась в начальной школе, в которую поступила в 1910 году. В сентябре в Чикабуми открылась начальная школа Мацу Камикавы, и она продолжила учёбу там. В 1916 году окончила начальную школу и поступила в среднюю школу Камикава Дайсан Дзиндзё. В 1917 году окончила женское профессионально-техническое училище Асахикавы, заняв четвёртое место среди 110 учениц. В 1920 году окончила женское ремесленное училище[1].\n" +
                "\n" +
                "Ещё в подростковом возрасте Юкиэ встретила в 1918 году известного японского лингвиста и исследователя айнского языка Кёсукэ Киндаичи в то время, когда он путешествовал по стране в национальный период Тайсё. Он путешествовал по Хоккайдо в поисках айнских сказителей, передающих устные песни и сказания. Он пришел в школу, где училась Юкиэ и сразу увидел способности и потенциал девушки. Он объяснила Чири ценность сохранения айнских сказок, пробудив в ней гордость за свой народ. Благодаря Кёсукэ она осознала ценность айнского народного эпоса, решила изучить и сохранить устные сказания своих предков, переложив их на бумагу[2].\n" +
                "\n" +
                "    Неужто… так просто исчезнут и богатый язык, который и в горе, и в радости использовали наши любимые предки, чтобы объясняться друг с другом, множество прекрасных слов, которые они нам оставили и которые мы привыкли слышать? О, слишком печально расставаться с ними! Какая жалость! Родившаяся в народе айну и выросшая в среде айнского языка, я неловко записала несколько малюсеньких историй из числа разнообразных рассказов, которые в увеселениях рассказывали наши предки.\n" +
                "    — Тюрленева Е. Г. Предисловие автора к «Собранию божественных песен Айну» (Айну синъёсю) Тири Юкиэ[3]\n" +
                "\n" +
                "В тетрадях, которые присылал ей Киндаити, Юкиэ записывала песни, услышанные от бабушки. Она использовала ромадзи для записи айнского языка, и затем переводила записанные ею тексты на японский. Так как айны не имели своей письменности, песенный эпос айнов был устной традицией, передаваемой из поколения в поколение. Тири Юкиэ стала первой среди айнов, кто переложил на бумагу песни и сказания своего народа. Песни сборника написаны на диалекте этого региона, который в нынешнее время является вымершим диалектом айнского языка. В 1920 году Тири Юкиэ начинает записывать песни камуи юкар, сохраняя интерес к айнскому устному фольклору[4].\n" +
                "\n" +
                "В 1922 году Киндаичи пригласил Юкиэ в Токио. В столице она закончила работу над сборником и через месяц умерла от сердечного приступа[2]. Сборник был опубликован после в 1923 году. В августе 1923 года была опубликована «Айну синёсю», переиздана в 1926 году, а затем в 1970 году. В 1973 году была опубликована биография Юкиэ «Полная серебряная капля» (яп. Gin no Shizuku Furu) написанная Хидэо Фудзимото. В 1978 году был опубликован перевод на эсперанто «Айну Синёсю». «Ainu Shinyoshu» — антология устного эпоса айнов, составленная Юкиэ Тири, была опубликована под названием «Айну синъёсю» («Собрание божественных песен айну») японским издательством «Иванами сётэн» (англ. Iwanami Bunko)[3].\n" +
                "\n" +
                "В программе NHK в 2007 году был показан сюжет «Время, когда история сдвинулась» о жизни и творчестве Юкиэ Тири, её имя стало широко известно[5].\n" +
                "\n" +
                "«Айну синъёсю» содержит 13 историй, которые айны пели на протяжении поколений. Это была первая работа по сохранению культурного устного наследия айнов, передавшая их уникальный взгляд на природу и веру. Лингвист доктор Кёсукэ Киндаичи назвал работу Юкиэ Тири «сокровищем вечности»[6].\n" +
                "\n" +
                "Брат Тири Юкиэ — Масихо Тири впоследствии также стал известным фольклористом и антропологом.\n" +
                "Описание\n" +
                "\n" +
                "Сборник «Айну синъёсю» — собрание из 13 песен жанра камуи юкар, что в переводе означает «песни богов». Он содержит 11 песен о природных богах и 2 песни, написанных от лица бога-человека Окикирмуи, то есть, сочинён и записан в жанре ойна. Камуи — это множество айнских богов. Айны обожествляли те проявления природы, которые были им непонятны или опасны. Камуи не просто обожествление природы, это души, существование которых на небесах не отличается от жизни людей на Земле. Спускаясь в земной мир и принимая облик животных, растений и других природных явлений, они помогают людям. Песни «Айну синъёсю» изложены от имени богов, являющихся представителями живой природы, и содержат в себе фрагменты из их жизни. В эпосе айнов видны их представления о жизни, наблюдения за окружающим миром, понятие о том, кем они видели себя в этом мире. Тири Юкиэ записала песни своего народа, чтобы сохранить его традиции. На основании её предисловия и того, что текст был записан на двух языках — айнском и японском, можно предположить, что своей целью она также ставила ознакомление японской публики с традициями и образом жизни айнов. Сборник песенного эпоса айнов является важным источником знаний о жизни, культуре, традициях и верованиях данного народа. На русский язык сборник был переведён по книге Катаяма Тацуминэ «Айну синъёсю о ёмитоку» [Katayama, 2003, p. 14–436] с использованием книги «Айну синъёсю» [Chiri, 1978, p. 5–183][3].\n" +
                "\n" +
                "В 2010 году в Ноборибэцу открыт мемориальный зал Тири Юкиэ — «Серебряная капля». В нём представлено около 300 предметов, связанных с людьми, внёсшими вклад в преемственность культуры айнов, в том числе, сувениры, связанные с жизнью Юкиэ, неопубликованные рукописные записи и первое издание Синёсю. Первый памятник культуры айнов получил международную аудиторию спустя почти 100 лет после своего первого выхода в свет. «Айну Синъёсю», или «Сборник айнских песен богов», составленный Тири Юкиэ, был переведён на десятки языков мира[5]. ";



        //Разбиение текста на слова, добавление их в массив и подсчёт.

            String[] words = someText.split("\\s");

        //Отфильтровываем символы и цифры.

            words = Arrays.stream(words)
                    .map(s -> s.toLowerCase().trim())       //добавлено 11.02 с целью подсчёт слов в разном регистре как одинаковых
                    .filter(s -> s.matches("^[аАбБвВгГдДеЕёЁжЖзЗиИйЙкКлЛмМнНоОпПрРсСтТуУфФхХцЦчЧшШщЩъЪыЫьЬэЭюЮяЯ]+$"))
                    .toArray(String[]::new);


        //Всего слов в отфильтрованном тексте.

        System.out.println("Всего слов в тексте: " + words.length);

       //Добавление мап. Далее, преобразование массива в Мап через стрим.

        Map<String, Long> mapOfWords = new HashMap<>();


        mapOfWords = Arrays.stream(words)
                .collect(Collectors.toMap(Function.identity(), v -> 1L, Long::sum));

        //Вывод 10 наиболее популярных слов из текста.

        mapOfWords.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(10).forEach(System.out::println);




    }
}
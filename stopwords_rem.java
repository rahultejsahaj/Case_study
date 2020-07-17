/// removing stop words from the text data from large files.

private List<String> getFileContentAsList(String resourceFilePath) throws IOException {

    File file = ResourceUtils.getFile(resourceFilePath);
    List<String> lines = Files.readAllLines(file.toPath());
    lines = lines.stream().map(line -> line.toLowerCase()).collect(Collectors.toList());

    return lines;

}

private List<String> splitLinesToWords(List<String> essayLines) {

    List<String> essayWords = new ArrayList<>();

    for (String line : essayLines) {
        List<String> words = Arrays.asList(line.split(" "));
        essayWords.addAll(words);
    }

    return essayWords;
}

private static final String resourceFilePath = "classpath:englishStopWords.txt";
private static final String sampleEssayFilePath = "classpath:sampleEssay.txt";


List<String> englishStopWords = getFileContentAsList(resourceFilePath);
List<String> essayLines = getFileContentAsList(sampleEssayFilePath);

List<String> essayWords = splitLinesToWords(essayLines);

essayWords.removeAll(englishStopWords);

long wordCountBeforeRemovingStopWords = essayWords.size();

 essayWords.removeAll(englishStopWords);

long wordCountAfterRemovingStopWords = essayWords.size();

logger.info("wordCountBeforeRemovingStopWords: " + wordCountBeforeRemovingStopWords);
logger.info("wordCountAfterRemovingStopWords: " + wordCountAfterRemovingStopWords);



@SpringBootApplication
public class App implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(App.class);

    private static final String resourceFilePath = "classpath:englishStopWords.txt";
    private static final String sampleEssayFilePath = "classpath:sampleEssay.txt";

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        try {

            List<String> englishStopWords = getFileContentAsList(resourceFilePath);

            logger.info("Stop words");
            logger.info(englishStopWords.toString());

            // I copied this essay from this place: https://www.apstudynotes.org/english/sample-essays/definition-success/
            List<String> essayLines = getFileContentAsList(sampleEssayFilePath);

            List<String> essayWords = splitLinesToWords(essayLines);

            long wordCountBeforeRemovingStopWords = essayWords.size();

            essayWords.removeAll(englishStopWords);

            long wordCountAfterRemovingStopWords = essayWords.size();

            logger.info("wordCountBeforeRemovingStopWords: " + wordCountBeforeRemovingStopWords);
            logger.info("wordCountAfterRemovingStopWords: " + wordCountAfterRemovingStopWords);

            logger.info("Essay after removing stop words: ");
            logger.info(essayWords.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> splitLinesToWords(List<String> essayLines) {

        List<String> essayWords = new ArrayList<>();

        for (String line : essayLines) {
            List<String> words = Arrays.asList(line.split(" "));
            essayWords.addAll(words);
        }

        return essayWords;
    }

    private List<String> getFileContentAsList(String resourceFilePath) throws IOException {

        File file = ResourceUtils.getFile(resourceFilePath);
        List<String> lines = Files.readAllLines(file.toPath());
        lines = lines.stream().map(line -> line.toLowerCase()).collect(Collectors.toList());

        return lines;

    }
}
package net.ingtra.pyokt;


import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import scala.collection.Seq;

public class OktWrapper {
    public static String normalize(String text) {
        return OpenKoreanTextProcessorJava.normalize(text).toString();
    }

    public static KoreanTokenJava[] tokenize(String text) {
        Seq<KoreanTokenizer.KoreanToken> tokenized = OpenKoreanTextProcessorJava.tokenize(text);
        return OpenKoreanTextProcessorJava.tokensToJavaKoreanTokenList(tokenized).toArray(new KoreanTokenJava[0]);
    }

    public static void main(String[] args) {
        tokenize("집에 가서 자고 싶다");
    }
}

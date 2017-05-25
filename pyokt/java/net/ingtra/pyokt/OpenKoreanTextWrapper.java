package net.ingtra.pyokt;


import org.openkoreantext.processor.KoreanPosJava;
import org.openkoreantext.processor.KoreanTokenJava;
import org.openkoreantext.processor.OpenKoreanTextProcessorJava;
import org.openkoreantext.processor.tokenizer.KoreanTokenizer;
import scala.collection.Seq;
import scala.collection.JavaConverters;

import java.util.LinkedList;
import java.util.List;


public class OpenKoreanTextWrapper {
    public static String normalize(String text) {
        return OpenKoreanTextProcessorJava.normalize(text).toString();
    }

    public static KoreanTokenJava[] tokenize(String text, Boolean normalize, Boolean stem) {
        if (normalize) {
            text = OpenKoreanTextWrapper.normalize(text);
        }

        Seq<KoreanTokenizer.KoreanToken> scalaTokens = OpenKoreanTextProcessorJava.tokenize(text);
        List<KoreanTokenizer.KoreanToken> tokens = JavaConverters.seqAsJavaList(scalaTokens);
        LinkedList<KoreanTokenJava> javaTokens = new LinkedList<>();

        for (KoreanTokenizer.KoreanToken token : tokens) {
            String tokenText = ((stem && token.stem().nonEmpty()) ? token.stem().get() : token.text());

            KoreanTokenJava javaToken = new KoreanTokenJava(
                    tokenText,
                    KoreanPosJava.valueOf(token.pos().toString()),
                    token.offset(),
                    token.length(),
                    token.unknown()
            );

            javaTokens.add(javaToken);
        }

        return javaTokens.toArray(new KoreanTokenJava[0]);
    }
}

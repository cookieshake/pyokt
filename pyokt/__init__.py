import os
import jpype


class KoreanToken:
    def __init__(self, kt_java):
        self.text = kt_java.getText()
        self.pos = kt_java.getPos().name()
        self.offset = kt_java.getOffset()
        self.length = kt_java.getLength()
        self.unknown = kt_java.isUnknown()

    def __str__(self):
        return '3'

    def __repr__(self):
        return '{}({}: {}, {})'.format(self.text, self.pos, self.offset, self.length)


def init_jvm():
    jvm_path = jpype.get_default_jvm_path()
    file_path = os.path.dirname(os.path.realpath(__file__))
    jars_path = os.path.join(file_path, 'jars')
    jars = [os.path.join(jars_path, f) for f in os.listdir(jars_path)]
    jpype.startJVM(jvm_path, '-Djava.class.path={}'.format(os.pathsep.join(jars)), '-Dfile.encoding=UTF8', '-ea')


def load_okt():
    return jpype.JPackage('net.ingtra.pyokt').OktWrapper

init_jvm()
okt = load_okt()
print([KoreanToken(t) for t in okt.tokenize("집에 가서 잠을 자고 싶다")])


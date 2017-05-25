import jpype
from pyokt.util import init_jvm, load_okt, java_bool


class KoreanToken:
    def __init__(self, kt_java):
        self.text = kt_java.getText()
        self.pos = kt_java.getPos().name()
        self.offset = kt_java.getOffset()
        self.length = kt_java.getLength()
        self.unknown = kt_java.isUnknown()

    def __str__(self):
        return self.pos

    def __repr__(self):
        return '{}({}: {}, {})'.format(self.text, self.pos, self.offset, self.length)


def tokenize(text, nomalize=False, stem=False):
    if not jpype.isJVMStarted():
        init_jvm()

    okt = load_okt()
    tokenized = okt.tokenize(text, java_bool(nomalize), java_bool(stem))

    return [KoreanToken(t) for t in tokenized]


def close():
    jpype.shutdownJVM()

# Dependencies
from tensorflow.python.keras.preprocessing.text import Tokenizer
from tensorflow.python.keras.preprocessing.sequence import pad_sequences
from flask import Flask, request, jsonify
from flask_cors import CORS
import traceback
import pandas as pd
import numpy as np
import tensorflow as tf
from gensim.models import KeyedVectors
import re
import jieba
import warnings
warnings.filterwarnings("ignore")

# Your API definition
app = Flask(__name__)

CORS(app)

def transform(text):
    # 去标点
    text = re.sub("[\s+\.\!\/_,$%^*(+\"\']+|[+——！，。？、~@#￥%……&*（）]+", "",text)
    # 分词
    cut = jieba.cut(text)
    
    cut_list = [ i for i in cut ]
    print('分词结果：',cut_list)
    # tokenize
    for i, word in enumerate(cut_list):
        try:
            cut_list[i] = cn_model.vocab[word].index
            if cut_list[i] >= 50000:
                cut_list[i] = 0
        except KeyError:
            cut_list[i] = 0
    # padding
    tokens_pad = pad_sequences([cut_list], maxlen=39,
                           padding='pre', truncating='pre')
    return tokens_pad

@app.route('/predict', methods=['GET','POST'])
def predict():
    if lr:
        try:
            message = request.form['content']

            result = lr.predict(transform(message))
            pred = np.argmax(result, axis=1)

            return jsonify({'prediction': str(pred[0])})

        except:

            return jsonify({'trace': traceback.format_exc()})
    else:
        print ('Train the model first')
        return ('No model here to use')

if __name__ == '__main__':
    cn_model = KeyedVectors.load_word2vec_format('C:\\Users\\Administrator\\Documents\\python爬虫\\embeddings\\sgns.zhihu.bigram', binary=False, unicode_errors="ignore")
    port = 5000

    lr = tf.keras.models.load_model('C:\\Users\\Administrator\\Desktop\\深度学习\\大作业\\RNN_SentimentAnalysis_model_3.h5')
    print ('Model loaded')

    app.run(port=port, debug=True)
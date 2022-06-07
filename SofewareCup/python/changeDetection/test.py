import paddle
import paddlers as pdrs
from skimage.io import imsave
import sys

save_path = "result.png"
def test(path1,path2):
    # 将导出模型所在目录传入Predictor的构造方法中
    predictor = pdrs.deploy.Predictor('inference_model', use_gpu=False)
    print(paddle.device.get_device())
    data = str(int(time.time())) + "1"
    # img_file参数指定输入图像路径
    result = predictor.predict(img_file=(path1, path2))
    # print(result)
    result = result[0]['label_map']
    # print(result)
    save_path = "C:\\Users\\Peter Hai\\Documents\\Tencent Files\\1556332597\\FileRecv\\SofewareCup\\src\\main\\resources\\static\\changeDetectorPredict\\results\\temp.png"
    imsave(save_path, result, check_contrast=False)
    return save_path
test(sys.argv[1],sys.argv[2])

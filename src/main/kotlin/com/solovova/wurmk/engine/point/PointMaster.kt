package com.solovova.wurmk.engine.point

import nu.pattern.OpenCV
import org.opencv.core.Core
import org.opencv.core.CvType
import org.opencv.core.Mat

class PointMaster {
    fun openDraw(){
        //OpenCV.loadShared()
        val mat = Mat.eye(3, 3, CvType.CV_8UC1)

        println("mat = " + mat.dump())

//        val cv2 = OpenCV()
//
//                draw = False # true if the mouse is pressed. Press m to shift into curve mode.
//        a,b = -1,-1
//
//        def draw_circle(event,x,y,flags,param):
//        global a,b,draw,mode
//        if(event == cv2.EVENT_LBUTTONDOWN):
//        draw = True
//        a,b = x,y
//        elif (event == cv2.EVENT_MOUSEMOVE):
//        if draw == True:
//        cv2.circle(img,(x,y),5,(0,0,255),-1)
//        elif(event == cv2.EVENT_LBUTTONUP):
//        draw = False
//        cv2.circle(img,(x,y),5,(0,0,255),-1)
//
//        img_grab = ImageGrab.grab(bbox=(0, 0, 1920, 1080)) #x, y, w, h
//        img = np.array(img_grab)
//
//        cv2.namedWindow("image", cv2.WND_PROP_FULLSCREEN)
//        cv2.setWindowProperty("image",cv2.WND_PROP_FULLSCREEN,cv2.WINDOW_FULLSCREEN)
//
//
//        cv2.setMouseCallback('image',draw_circle)
//        cv2.namedWindow('image')
//
//        while(True):
//        cv2.imshow("image", img)
//        key = cv2.waitKey(1)
//        if key == 27:
//        break
    }
}
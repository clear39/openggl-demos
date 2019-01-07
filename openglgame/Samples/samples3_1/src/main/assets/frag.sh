precision mediump float;
varying vec4 vColor;                    //  接受从顶点着色器过来的易变变量
void main(){
    gl_FragColor = vColor;              //给此片元赋值颜色值
}
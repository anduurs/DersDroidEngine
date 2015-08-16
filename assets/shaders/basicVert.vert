attribute vec3 v_Position;
attribute vec2 v_TexCoord;

uniform mat4 transformation;

varying vec4 color;

void main(){
	color = vec4(1,0,0,0);
	gl_Position = transformation * vec4(v_Position, 1);
}
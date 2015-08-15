attribute vec3 v_Position;

uniform mat4 transformation;

varying vec4 color;

void main(){
	color = vec4(1,1,1,1);
	gl_Position = transformation * vec4(v_Position, 1);
}
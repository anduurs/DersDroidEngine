attribute vec3 v_Position;
attribute vec2 v_TexCoord;

varying vec2 texCoord;
varying vec4 color;

uniform mat4 transformation;

void main(){
	texCoord = v_TexCoord;
	color = vec4(1,0,0,0);
	gl_Position = transformation * vec4(v_Position, 1);
}
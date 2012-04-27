#ifndef _vector3_h_
#define _vector3_h_

#include <gl/freeglut.h>
#include <iostream>



class vector3
{
public:
   GLfloat vec[3];            //Using C++ arrays for implementation

   vector3(void);
   vector3(GLfloat x, GLfloat y, GLfloat z);
   vector3(const GLfloat *v);
   vector3(const vector3 &v);
   vector3& operator= (const vector3& v);
   void set(GLfloat x, GLfloat y, GLfloat z);

   GLfloat& operator[](int i);
   GLfloat operator[](int i) const;

   vector3& operator+=(const vector3& v);
   vector3& operator-=(const vector3& v);
   vector3& operator*=(GLfloat s);
   vector3& operator/=(GLfloat s);
   vector3 operator+(const vector3& v) const;
   vector3 operator-(const vector3& v) const;
   vector3 operator*(GLfloat s) const;
   vector3 operator/(GLfloat s) const;
   vector3 operator-(void) const;
   bool operator==(const vector3& v) const;
   bool operator!=(const vector3& v) const;

   GLfloat length(void) const;
   GLfloat lengthSquared(void) const;
   void normalize(void);
   GLfloat dot(const vector3& v) const;
   vector3 cross(const vector3& v) const;
   GLfloat distance(const vector3& v) const;
   GLfloat distanceSquared(const vector3& v) const;

   void drawLine(void) const;

   static const vector3 Zero;
   static const vector3 X_Axis;
   static const vector3 Y_Axis;
   static const vector3 Z_Axis;

private:
};



/**
* Overloaded operator<< allows us to print the vector out in an understandable fashion.
* The format will be "(element1, element2, element3)"
* <param> s The ostream used for <<
* <param> v The vector that will be printed out
* <return> Returns an output stream in the form shown above
*/
inline std::ostream& operator<< (std::ostream& s, const vector3& v)
{
   return s << "(" << v.vec[0] << ", " << v.vec[1] << ", " << v.vec[2] << ")";
}

/**
* Overloaded operator* allows us to multiply a scalar times a vector3 object.
* It is inline here to improve performance.
* <param> s The scalar you want to multiply the vector by
* <param> v A vector3 object passed by constant reference
* <return> returns a vector3 obejct created by the scalar multiplication of the two parameters
*/
inline vector3 operator* (GLfloat s, const vector3& v)
{
   return v*s;
}

#endif

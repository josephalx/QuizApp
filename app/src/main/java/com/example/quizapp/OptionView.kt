package com.example.quizapp

import android.content.Context
import android.util.AttributeSet


class OptionView(context: Context, attrs: AttributeSet?): androidx.appcompat.widget.AppCompatTextView(context, attrs) {
    private  var stateCorrect = intArrayOf(R.attr.state_correct)
    private  var stateWrong = intArrayOf(R.attr.state_wrong)
    private  var  stateSelect = intArrayOf(R.attr.state_select)
private var correct:Boolean=false
private var wrong:Boolean=false
private  var select:Boolean=false
    init {
    context.theme.obtainStyledAttributes(attrs,R.styleable.Option,0,0).apply {
        try {
            correct=getBoolean(R.styleable.Option_correct,false)
            wrong=getBoolean(R.styleable.Option_wrong,false)
            select=getBoolean(R.styleable.Option_select,false)
    }
        finally {
            recycle()
        }}

}
   constructor(context: Context):this(context,null)
   {
       context.theme.obtainStyledAttributes(null,R.styleable.Option,0,0).apply {
           try {
               correct=getBoolean(R.styleable.Option_correct,false)
               wrong=getBoolean(R.styleable.Option_wrong,false)
               select=getBoolean(R.styleable.Option_select,false)
           }
           finally {
               recycle()
           }}
   }
    fun isCorrect():Boolean
    {
        return correct
    }
    fun setCorrect(value:Boolean)
    {
        correct=value
        invalidate()
        requestLayout()
        refreshDrawableState()
    }

    fun isWrong():Boolean
    {
        return wrong
    }
    fun setWrong(value:Boolean)
    {
        wrong=value

        refreshDrawableState()
    }

    fun isSelect():Boolean
    {
        return select
    }
    fun setSelect(value:Boolean)
    {
        select=value
//        invalidate()
//        requestLayout()
        refreshDrawableState()
    }



    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        val drawableState= super.onCreateDrawableState(extraSpace+3)

        if (select)
        {
            mergeDrawableStates(drawableState,stateSelect)
        }
        if(correct)
        {
            mergeDrawableStates(drawableState,stateCorrect)
        }

        if (wrong)
        {
            mergeDrawableStates(drawableState,stateWrong)
        }
            return drawableState

    }


}
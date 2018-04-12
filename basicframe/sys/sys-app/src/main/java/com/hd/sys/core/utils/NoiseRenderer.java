package com.hd.sys.core.utils;

import java.awt.image.BufferedImage;

import com.google.code.kaptcha.NoiseProducer;

import freemarker.core.Configurable;

/**
 * 验证码干扰自定义——无
 * @version	0.0.1
 * @author	<a href="mailto:wangjy@iwangding.com">王加友</a>
 * @date	2015-2-10 下午1:53:43
 */
public class NoiseRenderer extends Configurable implements NoiseProducer {

	@Override
	public void makeNoise(BufferedImage arg0, float arg1, float arg2,
			float arg3, float arg4) {
	}
}

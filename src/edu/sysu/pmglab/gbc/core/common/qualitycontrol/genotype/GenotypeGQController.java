package edu.sysu.pmglab.gbc.core.common.qualitycontrol.genotype;

import edu.sysu.pmglab.check.Assert;

import java.util.Map;

/**
 * @Data        :2021/06/15
 * @Author      :suranyi
 * @Contact     :suranyi.sysu@gamil.com
 * @Description :基因型水平Gq控制器
 */

public class GenotypeGQController implements IGenotypeQC {
    /**
     * GQ 字符
     */
    public static final String KEYWORD = "GQ";

    public static final int DEFAULT = 20;
    public static final int MIN = 0;
    public static final int MAX = Integer.MAX_VALUE - 2;

    int method;

    public GenotypeGQController() {
        this.method = DEFAULT;
    }

    public GenotypeGQController(int method) {
        Assert.valueRange(method, MIN, MAX);
        this.method = method;
    }

    @Override
    public boolean filter(Map<String, String> individual) {
        if (!individual.containsKey(KEYWORD)) {
            return true;
        } else {
            try {
                return Double.parseDouble(individual.get(KEYWORD)) >= this.method;
            } catch (NumberFormatException e) {
                return true;
            }
        }
    }

    @Override
    public boolean empty() {
        return method == MIN;
    }

    @Override
    public void setEmpty() {
        this.method = MIN;
    }

    @Override
    public String getKeyWord() {
        return KEYWORD;
    }

    @Override
    public int getMethod() {
        return this.method;
    }

    @Override
    public String toString() {
        return "GQ >= " + this.method;
    }
}

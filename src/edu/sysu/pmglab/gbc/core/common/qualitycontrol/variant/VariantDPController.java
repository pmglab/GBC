package edu.sysu.pmglab.gbc.core.common.qualitycontrol.variant;

import edu.sysu.pmglab.check.Assert;
import edu.sysu.pmglab.gbc.core.gtbcomponent.gtbreader.Variant;

import java.util.Map;

/**
 * @Data :2021/06/07
 * @Author :suranyi
 * @Contact :suranyi.sysu@gamil.com
 * @Description :位点DP控制器
 */

public class VariantDPController implements IVariantQC {
    /**
     * DP 字符
     */
    public static final String KEYWORD = "DP";
    public static final int DEFAULT = 0;
    public static final int MIN = 0;
    public static final int MAX = Integer.MAX_VALUE - 2;
    int method;

    public VariantDPController() {
        this.method = DEFAULT;
    }

    public VariantDPController(int method) {
        Assert.valueRange(method, MIN, MAX);
        this.method = method;
    }

    @Override
    public boolean filter(VCFNonGenotypeMarker marker) {
        Map<String, String> dict = marker.getInfo();
        if (!dict.containsKey(KEYWORD)) {
            return true;
        } else {
            try {
                return Double.parseDouble(dict.get(KEYWORD)) >= this.method;
            } catch (NumberFormatException e) {
                return true;
            }
        }
    }

    @Override
    public boolean filter(int alleleCount, int validAllelesNum) {
        return true;
    }

    @Override
    public boolean filter(Variant variant) {
        return true;
    }

    @Override
    public boolean empty() {
        return this.method == MIN;
    }

    @Override
    public void setEmpty() {
        this.method = MIN;
    }

    @Override
    public String toString() {
        return "DP >= " + this.method;
    }
}
